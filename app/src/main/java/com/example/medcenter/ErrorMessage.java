package com.example.medcenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class ErrorMessage extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Создание диалогового окна с надписью "Ошибка загрузки данных"
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ошибка загрузки данных")
                .setMessage("Проверьте подключение к интернету и повторите попытку.")
                .setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Закрытие диалогового окна при нажатии на кнопку
                        getActivity().finishAffinity();
                    }
                });
        return builder.create();
    }
}
