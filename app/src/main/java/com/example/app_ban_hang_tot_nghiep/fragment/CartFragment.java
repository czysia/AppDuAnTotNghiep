package com.example.app_ban_hang_tot_nghiep.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.adapter.CartAdapter;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentCartBinding;
import com.example.app_ban_hang_tot_nghiep.model.Cart;
import com.example.app_ban_hang_tot_nghiep.model.ItemCartMoreInfo;
import com.example.app_ban_hang_tot_nghiep.utils.Utils;
import com.example.app_ban_hang_tot_nghiep.viewmodel.CartViewModel;
import com.example.app_ban_hang_tot_nghiep.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.onItemClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    private FragmentCartBinding mBinding;
    private MainViewModel mViewModel;
    private CartViewModel mCartViewModel;
    private CartAdapter mAdapter;
    public List<ItemCartMoreInfo> mListData = new ArrayList<>();
    public Cart mCart;
    static CartFragment sCartFragment;
    SharedPreferences sharedPreferences;
    String token;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        sCartFragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        sCartFragment.setArguments(args);
        return sCartFragment;
    }

    public static CartFragment getInstance() {
        return sCartFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCartBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mCartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        setUpAdapter();
        mBinding.spinKit.setVisibility(View.VISIBLE);
        sharedPreferences = requireContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("tokenID", "xxx");
        Log.d("TAG567", "onCreateView: " + token);
        mCartViewModel.getListCartData(token);
        mCartViewModel.listCart.observe(getViewLifecycleOwner(), cart -> {
            mCart = cart;
            List<ItemCartMoreInfo> listItem = new ArrayList<>();
            Log.d("TAG444", "onCreateView: " + mViewModel.listSearch.size());
            mBinding.spinKit.setVisibility(View.GONE);
            if (mViewModel.listSearch.size() > 0) {

                for (int i = 0; i < cart.getProducts().size(); i++) {
                    for (int j = 0; j < mViewModel.listSearch.size(); j++) {
                        Log.d("TAG555", "onCreateView: " + cart.getProducts().get(i).getProductName() + "parent" + mViewModel.listSearch.get(j).getId().toString());
                        if (cart.getProducts().get(i).getProductId().equals(mViewModel.listSearch.get(j).getId())) {
                            ItemCartMoreInfo itemCart = new ItemCartMoreInfo();
                            itemCart.setAmount(cart.getProducts().get(i).getAmount());
                            itemCart.setPrice(cart.getProducts().get(i).getPrice());
                            itemCart.setImage(mViewModel.listSearch.get(j).getImage().get(0));
                            itemCart.setProductName(cart.getProducts().get(i).getProductName());
                            itemCart.setProductId(cart.getProducts().get(i).getProductId());
                            listItem.add(itemCart);
                        }
                    }
                }
                Log.d("TAG", "onCreateView: " + cart.getTotal());
                mBinding.setTotal(new Utils().convertMoney(cart.getTotal()));
                mListData.clear();
                mListData.addAll(listItem);
                mBinding.recycleCart.getAdapter().notifyDataSetChanged();
            }
        });
        mCartViewModel.deleteSuccess.observe(getViewLifecycleOwner(), data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            if (data) {
                Toast.makeText(requireContext(), "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(requireContext(), "Xóa sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        onClick();

        Log.d("TAG765", "onCreateView: " + mViewModel.listSearch.size());
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void ItemClick(ItemCartMoreInfo items) {

    }

    @Override
    public void onLongClick(ItemCartMoreInfo items) {
        new AlertDialog.Builder(requireContext()).setMessage(R.string.delete_item)
                .setTitle(R.string.delete_item_title)
                .setPositiveButton(R.string.yes, (arg0, arg1) -> {
                    mBinding.spinKit.setVisibility(View.VISIBLE);
                    mCartViewModel.deleteItemCart(items.getProductId(), token);
                })
                .setNegativeButton(R.string.no, (arg0, arg1) -> {

                })
                .show();
    }

    public void setUpAdapter() {
        mAdapter = new CartAdapter(mListData, getContext(), this);
        mBinding.recycleCart.setAdapter(mAdapter);
    }

    public void onClick() {
        mBinding.tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        mBinding.tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPay();
            }
        });
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

    }

    public void goToPay() {
        FragmentManager fragmentManager = getParentFragmentManager();
        PayFragment fragment = new PayFragment().newInstance(mCart.getTotal(), new PayFragment.onBackSuccess() {
            @Override
            public void onSuccess() {
                fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("cart")).commit();
            }
        });
        fragmentManager.beginTransaction().add(R.id.parent_content, fragment, "pay").commit();
    }
}