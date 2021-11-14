package com.example.mojodatlens.view.qrreader;

import android.Manifest;
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

import com.example.mojodatlens.databinding.FragmentQrReaderBinding;
import com.example.mojodatlens.view.assetslist.AssetsListQrReaderViewModel;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrReaderFragment extends Fragment implements ZXingScannerView.ResultHandler {

    //region Components
    private NavController navController;
    private FragmentQrReaderBinding binding;
    //endregion

    //region Life Cycle
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.viewCodeScanner.startCamera();
        navController = Navigation.findNavController(binding.getRoot());

        Dexter.withContext(getContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        binding.viewCodeScanner.setResultHandler(QrReaderFragment.this); // Register ourselves as a handler for scan results.
                        binding.viewCodeScanner.startCamera();          // Start camera on resume
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(requireContext(), "camera permission required", Toast.LENGTH_SHORT)
                                .show();
                        navController.navigateUp();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQrReaderBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.viewCodeScanner.setResultHandler(this); // Register ourselves as a handler for scan results.
        binding.viewCodeScanner.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.viewCodeScanner.stopCamera();           // Stop camera on pause
    }
    //endregion

    //region Override Lib
    @Override
    public void handleResult(Result rawResult) {
        AssetsListQrReaderViewModel assetsListQrReaderViewModel = new ViewModelProvider(requireActivity()).get(AssetsListQrReaderViewModel.class);
        assetsListQrReaderViewModel.code.postValue(rawResult.getText());
        navController.navigateUp();
        binding.viewCodeScanner.resumeCameraPreview(this);
    }
    //endregion

}


