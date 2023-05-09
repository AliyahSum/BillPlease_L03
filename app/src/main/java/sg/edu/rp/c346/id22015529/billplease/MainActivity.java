package sg.edu.rp.c346.id22015529.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amountDue ;
    EditText paxNumber ;
    ToggleButton noSVS ;
    ToggleButton gst ;
    EditText discountAmt ;
    RadioGroup rgPayment ;
    Button splitBill ;
    Button resetBill ;
    TextView totalBill ;
    TextView splitMoney ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountDue.findViewById(R.id.amountDue) ;
        paxNumber.findViewById(R.id.paxNumber) ;
        noSVS.findViewById(R.id.noSVSEnabled) ;
        gst.findViewById(R.id.gstEnabled) ;
        discountAmt.findViewById(R.id.discountAmount) ;
        rgPayment.findViewById(R.id.rgPaymentMethod) ;
        splitBill.findViewById(R.id.splitBill) ;
        resetBill.findViewById(R.id.resetBill) ;
        totalBill.findViewById(R.id.totalBill) ;
        splitMoney.findViewById(R.id.splitMoney) ;

        splitBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountDue.getText().toString() != "0" && paxNumber.getText().toString() != "0") {
                    double originalAmt = Double.parseDouble(amountDue.getText().toString()) ;
                    double newAmount = 0.0 ;

                    if (!noSVS.isChecked() && !gst.isChecked()) {
                        newAmount = originalAmt ;
                    }
                    else if (noSVS.isChecked() && !gst.isChecked()) {
                        newAmount = originalAmt * 1.1 ;
                    }
                    else if (!noSVS.isChecked() && gst.isChecked()) {
                        newAmount = originalAmt * 1.07 ;
                    }
                    else {
                        newAmount = originalAmt * 1.17 ;
                    }

                    if (discountAmt.getText().toString() != "0") {
                        newAmount *= 1 - Double.parseDouble(discountAmt.getText().toString()) / 100 ;
                    }

                    String paymentMethod = " in cash" ;
                    if (rgPayment.getCheckedRadioButtonId() == R.id.paymentPayNow) {
                        paymentMethod = " via PayNow to 912345678" ;
                    }

                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmount)) ;
                    int numPax = Integer.parseInt(paxNumber.getText().toString()) ;
                    if (numPax != 1) {
                        splitMoney.setText("Each Pays: $" + String.format("%.2f", newAmount/numPax) + paymentMethod) ;
                    }
                    else {
                        splitMoney.setText("Each Pays: $" + newAmount + paymentMethod) ;
                    }
                }
            }
        });
    }
}