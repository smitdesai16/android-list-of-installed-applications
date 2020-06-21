package me.creatorguy.androidlistofinstalledapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan = findViewById(R.id.btnScan);

        btnScan.setOnClickListener(this);
    }

    private boolean isSystemPackage(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnScan:
                List<String> installedApplications = new ArrayList<String>();
                List installedPackages = getPackageManager().getInstalledPackages(0);
                for (int i=0; i< installedPackages.size(); i++) {
                    PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                    if(!isSystemPackage(packageInfo)) {
                        installedApplications.add(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
                    }
                }
                Toast.makeText(this, installedApplications.toString(), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}