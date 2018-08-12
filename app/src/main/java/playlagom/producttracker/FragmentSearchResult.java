package playlagom.producttracker;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by User on 12/11/2017.
 */

public class FragmentSearchResult extends Fragment
        implements View.OnClickListener{

    Communicator communicator;
    TextView letMeChoice;
    LinearLayout llPayment;
    Button btnConfirmOrder;

    ImageView ivProductCenter, ivProductLeft, ivProductRight;
    TextView tvShopNameCenter, tvShopNameLeft, tvShopNameRight;
    TextView tvPriceCenter, tvPriceLeft, tvPriceRight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator = (Communicator) getActivity();

        // Row 1: Optimistic most popular results
        // Center components
        ivProductCenter = getActivity().findViewById(R.id.ivProductCenter);
        ivProductCenter.setOnClickListener(this);
//        tvShopNameCenter = getActivity().findViewById(R.id.tvShopNameCenter);
//        tvShopNameCenter.setOnClickListener(this);
//        tvPriceCenter = getActivity().findViewById(R.id.tvPriceCenter);
//        tvPriceCenter.setOnClickListener(this);

        // Right components
        ivProductRight = getActivity().findViewById(R.id.ivProductRight);
        ivProductRight.setOnClickListener(this);
//        tvShopNameRight = getActivity().findViewById(R.id.tvShopNameRight);
//        tvShopNameRight.setOnClickListener(this);
//        tvPriceRight = getActivity().findViewById(R.id.tvPriceRight);
//        tvPriceRight.setOnClickListener(this);

        // Left components
        ivProductLeft = getActivity().findViewById(R.id.ivProductLeft);
        ivProductLeft.setOnClickListener(this);
//        tvPriceLeft = getActivity().findViewById(R.id.tvPriceLeft);
//        tvPriceLeft.setOnClickListener(this);
//        tvShopNameLeft = getActivity().findViewById(R.id.tvShopNameLeft);
//        tvShopNameLeft.setOnClickListener(this);

        // Row 2: Payment | Let me choice
        llPayment = getActivity().findViewById(R.id.llPayment);
        llPayment.setOnClickListener(this);
        letMeChoice = getActivity().findViewById(R.id.letMeChoice);
        letMeChoice.setOnClickListener(this);
        // Row 3: Button
        btnConfirmOrder = getActivity().findViewById(R.id.btnConfirmOrder);
        btnConfirmOrder.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivProductLeft:
                Toast.makeText(getActivity().getApplicationContext(),
                        "Suggestive shop 1 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivProductCenter:
                Toast.makeText(getActivity().getApplicationContext(),
                        "Choiced most popular item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivProductRight:
                Toast.makeText(getActivity().getApplicationContext(),
                        "Suggestive shop 2 clicked", Toast.LENGTH_SHORT).show();

            case R.id.llPayment:
                Toast.makeText(getActivity().getApplicationContext(),
                    "Payment option: UNDER CONSTRUCTION", Toast.LENGTH_SHORT).show();
                break;
            case R.id.letMeChoice:
                Toast.makeText(getActivity().getApplicationContext(),
                        "Manual choice options", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                startActivity(intent);
                break;
            case R.id.btnConfirmOrder:
                Toast.makeText(getActivity().getApplicationContext(),
                        "Confirm order: Updating soon...", Toast.LENGTH_SHORT).show();

                break;
            default: break;
        }
    }
}
