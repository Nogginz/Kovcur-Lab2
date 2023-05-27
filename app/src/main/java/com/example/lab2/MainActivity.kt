package com.example.lab2
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var loginLocked: TextView
    private lateinit var attempts: TextView
    private lateinit var numberOfAttempts: TextView
    // Число для подсчета попыток залогиниться:
    private var numberOfRemainingLoginAttempts = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById(R.id.edit_user)
        password = findViewById(R.id.edit_password)
        login = findViewById(R.id.button_login)
        loginLocked = findViewById(R.id.login_locked)
        attempts = findViewById(R.id.attempts)
        numberOfAttempts =
            findViewById(R.id.number_of_attempts)
        numberOfAttempts.text =
            numberOfRemainingLoginAttempts.toString()
    }
    // Обрабатываем нажатие кнопки "Войти":
    fun Login(view: android.view.View) {
// Если введенные логин и пароль будут словом "admin",
// показываем Toast сообщение об успешном входе:
        if (username.text.toString() == "Samoylov-A"
            && password.text.toString() == "admin") {
            Toast.makeText(applicationContext, "Вход выполнен, Александр!", Toast.LENGTH_SHORT).show()
// Выполняем переход на другой экран:
            val intent = Intent(this, Second::class.java)
            startActivity(intent)
        }
// В другом случае выдаем сообщение с ошибкой:
        else {
            Toast.makeText(applicationContext, "Неправильные данные!", Toast.LENGTH_SHORT).show()
            numberOfRemainingLoginAttempts--
// Делаем видимыми текстовые поля, указывающие на количество оставшихся попыток:
            attempts.visibility = View.VISIBLE
            numberOfAttempts.visibility = View.VISIBLE
            numberOfAttempts.text = numberOfRemainingLoginAttempts.toString()
// Когда выполнено 3 безуспешных попытки залогиниться,
// делаем видимым текстовое поле с надписью, что все пропало и выставляем
// кнопке настройку невозможности нажатия
                    if (numberOfRemainingLoginAttempts == 0) {
                        login.isEnabled = false
                        loginLocked.visibility = View.VISIBLE
                        loginLocked.setBackgroundColor(Color.RED)
                        loginLocked.text = "Вход заблокирован!!!"
                    }
        }
    }
}