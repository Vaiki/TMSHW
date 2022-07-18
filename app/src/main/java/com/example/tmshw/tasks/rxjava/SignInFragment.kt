package com.example.tmshw.tasks.rxjava

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tmshw.R
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit


class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val data = mapOf("sobaka@gmail.com" to "23123fkS")
    private var enteredEmail: String? = null
    private var enteredPass: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val enterEmail = view?.findViewById<EditText>(R.id.enterEmail)
        val enterPassword = view?.findViewById<EditText>(R.id.enterPassword)
        val passwordError = view?.findViewById<TextInputLayout>(R.id.passwordError)
        val emailError = view?.findViewById<TextInputLayout>(R.id.emailError)
        val singIn = view?.findViewById<Button>(R.id.buttonSignUp)

        RxTextView.afterTextChangeEvents(enterEmail!!)
            .skipInitialValue()
            .map {
                enterEmail.error = null
                it.view().text.toString()
            }
            .debounce(400, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            .compose(validateEmailAddress)
            .compose(retryWhenError {
                emailError?.error = it.message
            })
            .subscribe {
                enteredEmail = it
                Log.e("Email", it)
            }

        RxTextView.afterTextChangeEvents(enterPassword!!)
            .skipInitialValue()
            .map {
                passwordError?.error = null
                it.view().text.toString()
            }
            .debounce(400, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            .compose(validatePassword)
            .compose(retryWhenError {
                passwordError?.error = it.message
            })
            .subscribe {
                enteredPass = it
                Log.e("Pass", it)
            }

        RxView.clicks(singIn!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                for ((key, value) in data) {
                    if (key == enteredEmail.toString() && value == enteredPass.toString()) {
                        Toast.makeText(context, "Entrance allowed", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(
                            context, "Incorrect login or password", Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }


        return inflater.inflate(R.layout.fragment_sign_in, container, false)

    }

    //If the app encounters an error, then try again//
    private inline fun retryWhenError(crossinline onError: (ex: Throwable) -> Unit): ObservableTransformer<String, String> =
        ObservableTransformer { observable ->
            observable.retryWhen { errors ->
                ///Use the flatmap() operator to flatten all emissions into a single Observable//
                errors.flatMap {
                    onError(it)
                    Observable.just("")
                }
            }
        }


    //Define our ObservableTransformer and specify that the input and output must be a string//
    private val validatePassword = ObservableTransformer<String, String> { observable ->
        observable.flatMap {
            Observable.just(it).map { it.trim() }

                .filter { it.length > 7 }
                //If the password is less than 7 characters, then throw an error//
                .singleOrError()
                //If an error occurs, then display the following message//
                .onErrorResumeNext {
                    if (it is NoSuchElementException) {
                        Single.error(Exception("Your password must be 7 characters or more"))

                    } else {
                        Single.error(it)
                    }
                }
                .toObservable()
        }
    }

    //Define an ObservableTransformer, where we’ll perform the email validation//
    private val validateEmailAddress = ObservableTransformer<String, String> { observable ->
        observable.flatMap {
            Observable.just(it).map { it.trim() }
                //Check whether the user input matches Android’s email pattern//
                .filter {
                    Patterns.EMAIL_ADDRESS.matcher(it).matches()
                }
                //If the user’s input doesn’t match the email pattern, then throw an error//
                .singleOrError()
                .onErrorResumeNext {
                    if (it is NoSuchElementException) {
                        Single.error(Exception("Please enter a valid email address"))
                    } else {
                        Single.error(it)
                    }
                }
                .toObservable()
        }
    }
    private val verificationPassword = ObservableTransformer<String, String> { observable ->
        observable.flatMap {
            Observable.just(it).map { it.trim() }
                .filter { data.containsValue(it) }
        }
    }


}