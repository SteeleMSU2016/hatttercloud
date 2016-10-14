package edu.msu.cse476.cloudhatter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Jason Steele on 11/5/2015.
 */
public class DeleteDlg extends DialogFragment {
    /**
    * Create the dialog box
    * @param savedInstanceState The saved instance bundle
    */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set the title
        builder.setTitle(R.string.delete_fm_title);

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Pass null as the parent view because its going in the dialog layout
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.catalog_dlg, null);
        builder.setView(view);

        // Add a cancel button
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int id) {
                // Cancel just closes the dialog box
            }
        });

        final AlertDialog dlg = builder.create();

        // Find the list view
        ListView list = (ListView)view.findViewById(R.id.listHattings);

        // Create an adapter
        final Cloud.CatalogAdapter adapter = new Cloud.CatalogAdapter(list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position,  long id) {
            final AlertDialog dlg = builder.create();
            // Get the id of the one we want to load
            String catId = adapter.getId(position);

            // Dismiss the dialog box
            dlg.dismiss();

            DeletingDlg DeleteDlg = new DeletingDlg();
            DeleteDlg.setCatId(catId);
            DeleteDlg.show(getActivity().getFragmentManager(), "Deleting");
            }

        });


        return dlg;
    }
}


