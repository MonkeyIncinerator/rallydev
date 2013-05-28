package com.rallydev.intellij.task.ui

import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import com.intellij.openapi.ui.Messages
import com.rallydev.intellij.wsapi.ConnectionTest
import org.apache.commons.httpclient.auth.InvalidCredentialsException

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class TestConnectionButtonListener implements ActionListener {

    RepositoryEditorImpl form

    TestConnectionButtonListener(RepositoryEditor form) {
        this.form = form
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String error = null
        try {
            new ConnectionTest(form.getClient()).doTest()
        } catch (Exception e) {
            error = messageFromException(e)
        }
        if (error) {
            form.showErrorDialog(error, 'Error')
        } else {
            form.showMessageDialog('Connection is successful', 'Connection')
        }
    }

    private static String messageFromException(Exception e) {
        switch (e.class) {
            case InvalidCredentialsException:
                "Invalid credentials: ${e.getMessage()}"
                break
            case MalformedURLException:
                "Invalid URL: ${e.message}"
                break
            case UnknownHostException:
                "Unknown host: ${e.message}"
                break
            case MalformedJsonException:
            case JsonSyntaxException:
                "The server responded incorrectly (check the URL)"
                break
            default:
                "Unknown error: ${e.message}"
        }
    }

}
