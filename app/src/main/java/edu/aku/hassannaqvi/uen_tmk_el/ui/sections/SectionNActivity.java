package edu.aku.hassannaqvi.uen_tmk_el.ui.sections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.uen_tmk_el.CONSTANTS;
import edu.aku.hassannaqvi.uen_tmk_el.R;
import edu.aku.hassannaqvi.uen_tmk_el.contracts.FormsContract;
import edu.aku.hassannaqvi.uen_tmk_el.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_tmk_el.core.MainApp;
import edu.aku.hassannaqvi.uen_tmk_el.databinding.ActivitySectionDBinding;
import edu.aku.hassannaqvi.uen_tmk_el.databinding.ActivitySectionNBinding;
import edu.aku.hassannaqvi.uen_tmk_el.models.Form;
import edu.aku.hassannaqvi.uen_tmk_el.ui.other.EndingActivity;
import edu.aku.hassannaqvi.uen_tmk_el.utils.AppUtilsKt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.aku.hassannaqvi.uen_tmk_el.core.MainApp.form;

public class SectionNActivity extends AppCompatActivity {

    ActivitySectionNBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_n);
        bi.setCallback(this);
        setupSkip();
    }

    private void setupSkip() {

    }

    public void BtnContinue() throws JSONException {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("can1", bi.can101.isChecked() ? "1"
                : bi.can102.isChecked() ? "2"
                :  "-1");

        json.put("can2", bi.can2.getText().toString());

        json.put("can3", bi.can3.getText().toString());

        json.put("can4", bi.can401.isChecked() ? "1"
                : bi.can402.isChecked() ? "2"
                : bi.can403.isChecked() ? "3"
                :  "-1");

        json.put("can5", bi.can501.isChecked() ? "1"
                : bi.can502.isChecked() ? "98"
                :  "-1");

        json.put("can501x", bi.can501x.getText().toString());
        json.put("can6", bi.can6.getText().toString());

        json.put("can7", bi.can701.isChecked() ? "1"
                : bi.can702.isChecked() ? "2"
                : bi.can703.isChecked() ? "3"
                :  "-1");

        json.put("can8", bi.can801.isChecked() ? "1"
                : bi.can802.isChecked() ? "2"
                : bi.can803.isChecked() ? "3"
                :  "-1");

        json.put("can9", bi.can901.isChecked() ? "1"
                : bi.can902.isChecked() ? "2"
                : bi.can903.isChecked() ? "3"
                :  "-1");

        json.put("can10", bi.can10.getText().toString());

        json.put("can11", bi.can11.getText().toString());

        json.put("can12", bi.can1201.isChecked() ? "1"
                : bi.can1202.isChecked() ? "2"
                :  "-1");

        json.put("can13", bi.can13.getText().toString());

        json.put("can14", bi.can14.getText().toString());

        json.put("can15", bi.can15.getText().toString());

        json.put("can16", bi.can1601.isChecked() ? "1"
                : bi.can1602.isChecked() ? "2"
                :  "-1");

        json.put("can17", bi.can17.getText().toString());

        json.put("can18", bi.can18.getText().toString());

        json.put("can19", bi.can19.getText().toString());

        json.put("can20", bi.can2001.isChecked() ? "1"
                : bi.can2002.isChecked() ? "2"
                :  "-1");

        json.put("can21", bi.can21.getText().toString());


    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }

}