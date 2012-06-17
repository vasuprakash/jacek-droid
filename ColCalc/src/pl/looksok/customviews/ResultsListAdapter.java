package pl.looksok.customviews;

import java.util.Currency;
import java.util.List;
import java.util.Locale;

import pl.looksok.R;
import pl.looksok.logic.PersonData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ResultsListAdapter extends ArrayAdapter<PersonData> {

	private List<PersonData> items;
	private int layoutResourceId;
	private Context context;

	public ResultsListAdapter(Context context, int layoutResourceId, List<PersonData> items) {
		super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
		this.items = items;
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ResultHolder holder = null;
        
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ResultHolder();
            holder.txtName = (TextView)row.findViewById(R.id.calcItem_textView_name);
            holder.txtBalance = (TextView)row.findViewById(R.id.calcItem_textView_personPay);
            holder.txtCurrency = (TextView)row.findViewById(R.id.calcItem_textView_currency);
            
            row.setTag(holder);
        } else {
            holder = (ResultHolder)row.getTag();
        }

        PersonData pp = items.get(position);
        holder.txtName.setText(pp.getName());
        holder.txtBalance.setText(String.valueOf(pp.getTotalRefundForThisPerson()));
        holder.txtCurrency.setText(Currency.getInstance(Locale.getDefault()).getSymbol());
        
        return row;
    }
	
    static class ResultHolder {
    	TextView txtName;
    	TextView txtBalance;
    	TextView txtCurrency;
    }
}
