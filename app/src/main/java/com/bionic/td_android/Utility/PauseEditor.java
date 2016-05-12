package com.bionic.td_android.Utility;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bionic.td_android.MainWindow.CreateShift.ShiftPageBuilder;
import com.bionic.td_android.R;

/**
 * Created by user on 11.04.2016.
 */
public class PauseEditor extends DialogFragment {

    private ShiftPageBuilder shift;

    public PauseEditor(ShiftPageBuilder shift) {
        this.shift = shift;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.dialog_edit_pause, null);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = (EditText) view.findViewById(R.id.dialog_time);
                        String time = editText.getText().toString();
                        if(shift.setCustomPauseTime(time) == true){
                            PauseEditor.this.getDialog().cancel();
                        }else {
                            Toast.makeText(view.getContext(),"Wrong input",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PauseEditor.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

}
