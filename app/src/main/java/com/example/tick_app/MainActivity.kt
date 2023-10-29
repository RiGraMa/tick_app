package com.example.tick_app
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    private lateinit var txtBox1: EditText
    private lateinit var txtBox2: EditText
    private lateinit var txtBox3: EditText
    private lateinit var txtBox4: EditText
    private lateinit var txtBox5: EditText

    private lateinit var CheckBox1: CheckBox
    private lateinit var CheckBox2: CheckBox
    private lateinit var CheckBox3: CheckBox
    private lateinit var CheckBox4: CheckBox
    private lateinit var CheckBox5: CheckBox
    private lateinit var CheckBox6: CheckBox
    private lateinit var emailButton: Button

    private lateinit var completeLBL: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtBox1 = findViewById(R.id.txtBox1)
        txtBox2 = findViewById(R.id.txtBox2)
        txtBox3 = findViewById(R.id.txtbox3)
        txtBox4 = findViewById(R.id.txtbox4)
        txtBox5 = findViewById(R.id.txtbox5)

        CheckBox1 = findViewById(R.id.checkBox1)
        CheckBox2 = findViewById(R.id.checkBox2)
        CheckBox3 = findViewById(R.id.checkBox3)
        CheckBox4 = findViewById(R.id.checkBox4)
        CheckBox5 = findViewById(R.id.checkBox5)
        CheckBox6 = findViewById(R.id.checkBox6)

        completeLBL = findViewById(R.id.completeLBL)

        emailButton = findViewById<Button>(R.id.emailButton)

        emailButton.setOnClickListener {
            sendEmail()
        }

        CheckBox6.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Reset all checkboxes to unchecked
                CheckBox1.isChecked = false
                CheckBox2.isChecked = false
                CheckBox3.isChecked = false
                CheckBox4.isChecked = false
                CheckBox5.isChecked = false

                // Clear the label
                completeLBL.text = ""
            }
        }


        val checkBoxListener = { _: Boolean ->
            if (CheckBox1.isChecked && CheckBox2.isChecked && CheckBox3.isChecked && CheckBox4.isChecked && CheckBox5.isChecked) {
                completeLBL.text = "List complete"
            } else {
                completeLBL.text = "" // If not all checkboxes are checked, you can set the label to an empty string or something else.
            }
        }

        CheckBox1.setOnCheckedChangeListener { _, isChecked -> checkBoxListener(isChecked) }
        CheckBox2.setOnCheckedChangeListener { _, isChecked -> checkBoxListener(isChecked) }
        CheckBox3.setOnCheckedChangeListener { _, isChecked -> checkBoxListener(isChecked) }
        CheckBox4.setOnCheckedChangeListener { _, isChecked -> checkBoxListener(isChecked) }
        CheckBox5.setOnCheckedChangeListener { _, isChecked -> checkBoxListener(isChecked) }



    }
    private fun sendEmail() {
        val subject = "Completed List"
        val message = "Here is the completed list:\n" +
                "Item 1: ${txtBox1.text}\n" +
                "Item 2: ${txtBox2.text}\n" +
                "Item 3: ${txtBox3.text}\n" +
                "Item 4: ${txtBox4.text}\n" +
                "Item 5: ${txtBox5.text}"

        // Create an Intent to send an email
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:") // Use the "mailto:" URI
        emailIntent.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf("ricardo.martins.1911@gmail.com.com")
        ) // Replace with your email address
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, message)

        // Verify that there is an email client available to handle the intent

        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(emailIntent)

            // Show a toast message to inform the user that the email was sent
            Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show()
        } else {
            // If there is no email client available, show a toast with an error message
            Toast.makeText(this, "No email client available", Toast.LENGTH_SHORT).show()
        }
    }
}