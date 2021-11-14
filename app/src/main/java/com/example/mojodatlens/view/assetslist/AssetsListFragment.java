package com.example.mojodatlens.view.assetslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mojodatlens.R;
import com.example.mojodatlens.databinding.FragmentAssetsListBinding;

public class AssetsListFragment extends Fragment {

    //region Components
    private FragmentAssetsListBinding binding;
    //endregion

    //region Life Cycle
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAssetsListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(binding.getRoot());
        AssetsListViewModel assetsListViewModel = new ViewModelProvider(AssetsListFragment.this).get(AssetsListViewModel.class);
        AssetsListQrReaderViewModel assetsListQrReaderViewModel = new ViewModelProvider(requireActivity()).get(AssetsListQrReaderViewModel.class);

        assetsListQrReaderViewModel.code.observe(getViewLifecycleOwner(), code -> {
            if (code.isEmpty()){
                hideAllAssetInformation();
                return;
            }
            assetsListViewModel.findByBarcode(code).observe(getViewLifecycleOwner(), asset -> {
                if (asset != null){
                    showAllAssetInformation(asset.getBarcode(), asset.getAssetCategory(), asset.getAssetDescription());
                } else {
                    hideAllAssetInformation();
                    binding.assetsListImageViewNoData.setVisibility(View.VISIBLE);
                }
            });
        });

        binding.mainActivityButtonSearch.setOnClickListener(view1 -> {
            assetsListQrReaderViewModel.code.postValue(binding.assetsListEditTextBarcode.getText().toString());
        });

        binding.mainActivityButtonReadQrCode.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_assetsListFragment_to_qrReaderFragment);
        });
    }
    //endregion

    //region Methods
    private void hideAllAssetInformation(){
        binding.assetsListTextViewAssetInformation.setVisibility(View.GONE);
        binding.assetsListTextViewBarcode.setVisibility(View.GONE);
        binding.assetsListTextViewCategory.setVisibility(View.GONE);
        binding.assetsListTextViewDescription.setVisibility(View.GONE);

        binding.assetsListImageViewNoData.setVisibility(View.VISIBLE);
        binding.assetsListTextViewNoDataFound.setVisibility(View.VISIBLE);
        binding.assetsListTextViewNoDataFound.setText(getResources().getText(R.string.no_data_found));
    }

    private void showAllAssetInformation(String barcode, String category, String description){
        binding.assetsListTextViewAssetInformation.setVisibility(View.VISIBLE);
        binding.assetsListTextViewBarcode.setVisibility(View.VISIBLE);
        binding.assetsListTextViewBarcode.setText(getResources().getText(R.string.barcode) + " " + barcode);
        binding.assetsListTextViewCategory.setVisibility(View.VISIBLE);
        binding.assetsListTextViewCategory.setText(getResources().getText(R.string.category) + " " + category);
        binding.assetsListTextViewDescription.setVisibility(View.VISIBLE);
        binding.assetsListTextViewDescription.setText(getResources().getText(R.string.description) + " " + description);

        binding.assetsListImageViewNoData.setVisibility(View.GONE);
        binding.assetsListTextViewNoDataFound.setVisibility(View.GONE);
    }
    //endregion
}
