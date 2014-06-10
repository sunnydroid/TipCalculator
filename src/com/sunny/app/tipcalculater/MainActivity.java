package com.sunny.app.tipcalculater;

import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final Logger logger = Logger.getLogger("MainActivityLogger");
	
	private float totalBill;
	private int tip;
	private float totalTip;
	private float totalBillWithTip;
	EditText etTotalBill;
	NumberPicker npTipPercent;
	TextView tvTotalTip;
	TextView tvTotalBillWithTip;
	TextView tvErrorMessage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etTotalBill = (EditText) findViewById(R.id.etTipTotal);
		npTipPercent = (NumberPicker) findViewById(R.id.npTipPercent);
		npTipPercent.setMaxValue(1000);
		npTipPercent.setMinValue(0);
		npTipPercent.setValue(15);
		tvTotalTip = (TextView) findViewById(R.id.tvTotalTip);
		tvTotalBillWithTip = (TextView) findViewById(R.id.tvTotalBillWithTip);
		tvErrorMessage = (TextView) findViewById(R.id.tvErrorMessage);
		
		initListeners();
	}
	
	private void updateTextViewAmounts() {
		tvErrorMessage.setText("");
		tip = npTipPercent.getValue();
		try {			
			totalBill = Float.parseFloat(etTotalBill.getText().toString());
		} catch (NumberFormatException nfe) {
			logger.log(Level.SEVERE, "Number format exception for total bill");
			tvErrorMessage.setText("Not a valid amount");
			return;
		}
		
		totalTip = (tip * totalBill) / 100;
		totalBillWithTip = totalBill + totalTip;
		
		tvTotalTip.setText(String.format("%.2f", totalTip));
		tvTotalBillWithTip.setText(String.format("%.2f", totalBillWithTip));
	}
	
	private void initListeners() {
		etTotalBill.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				updateTextViewAmounts();
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		npTipPercent.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				updateTextViewAmounts();
				
			}
		});
	}
	
}
