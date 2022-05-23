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
import com.example.app_ban_hang_tot_nghiep.model.ItemProductCart;
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
    public List<ItemProductCart> mListData = new ArrayList<>();
    public Cart mCart;
    public int totalMoney = 0;
    Boolean isDelete = false;
    static CartFragment sCartFragment;
    SharedPreferences sharedPreferences;
    String token;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onPlusClick(ItemProductCart items) {
        List<ItemProductCart> data = new ArrayList<>();
        data.clear();
        data.addAll(mListData);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getVariantId().equals(items.getVariantId())) {
                data.get(i).setAmount(items.getAmount() + 1);
            }
        }
        mCartViewModel.updateCart(token, data);
        mBinding.spinKit.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMinusClick(ItemProductCart items) {
        List<ItemProductCart> data = new ArrayList<>();
        data.clear();
        data.addAll(mListData);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getVariantId().equals(items.getVariantId())) {
                if (data.get(i).getAmount() == 1) {
                    new AlertDialog.Builder(requireContext()).setMessage(R.string.delete_item)
                            .setTitle(R.string.delete_item_title)
                            .setPositiveButton(R.string.yes, (arg0, arg1) -> {
                                mBinding.spinKit.setVisibility(View.VISIBLE);
                                mCartViewModel.deleteItemCart(items.getVariantId(), token);
                                isDelete = true;
                            })
                            .setNegativeButton(R.string.no, (arg0, arg1) -> {

                            })
                            .show();
                } else {
                    data.get(i).setAmount(items.getAmount() - 1);
                }
            }
        }
        if (!isDelete) {
            mBinding.spinKit.setVisibility(View.VISIBLE);
            mCartViewModel.updateCart(token, data);
            isDelete = false;
        }
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
        setUpViewModel();
        onClick();

        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void ItemClick(ItemProductCart items) {

    }

    @Override
    public void onLongClick(ItemProductCart items) {

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
                if (totalMoney <= 0) {
                    Toast.makeText(requireContext(), "Không có sản phẩm nào trong giỏ hàng", Toast.LENGTH_SHORT).show();
                    return;
                }
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

    public void setUpViewModel() {
        mCartViewModel.getListCartData(token);
        mCartViewModel.listCart.observe(getViewLifecycleOwner(), cart -> {
            mCart = cart;
            mBinding.spinKit.setVisibility(View.GONE);
            totalMoney = cart.getTotal();
            mBinding.setTotal(new Utils().convertMoney(cart.getTotal()));
            if (cart.getProducts().size() > 0) {
                mBinding.group.setVisibility(View.GONE);
            } else {
                mBinding.group.setVisibility(View.VISIBLE);
            }
            mListData.clear();
            mListData.addAll(cart.getProducts());
            mBinding.recycleCart.getAdapter().notifyDataSetChanged();
        });
        mCartViewModel.deleteSuccess.observe(getViewLifecycleOwner(), data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            isDelete = false;
            if (data) {
                Toast.makeText(requireContext(), "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(requireContext(), "Xóa sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        mCartViewModel.updateData.observe(getViewLifecycleOwner(), data -> {
            if (data) {
                mCartViewModel.getListCartData(token);
            } else {
                mBinding.spinKit.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Thay đổi không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}