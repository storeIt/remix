package com.example.remix.impl.news;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.remix.R;
import com.example.remix.base.ui.FragmentBase;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class NewsDetailsFragment extends FragmentBase {

    private ImageView ivBack;
    private ImageView ivDelete;
    private TextInputEditText tetTitle;
    private TextInputEditText tetDescription;
    private Button btnSave;

    private NewsViewModel viewModel;

    public NewsDetailsFragment() {
        super(R.layout.fragment_news_details);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(NewsViewModel.class);
        viewModel.init();
    }

    @Override
    protected void initViews() {

        ivBack = requireView().findViewById(R.id.iv_back);
        ivDelete = requireView().findViewById(R.id.iv_delete);
        tetTitle = requireView().findViewById(R.id.tet_title);
        tetDescription = requireView().findViewById(R.id.tet_description);
        btnSave = requireView().findViewById(R.id.btn_save);
        populateFieldsOnEdit();
    }

    @Override
    protected void attachListeners() {

        ivBack.setOnClickListener(this::navigateBack);

        ivDelete.setOnClickListener(view -> {
            deleteNews();
        });

        tetTitle.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                enableSave();
            }
        });

        tetDescription.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                enableSave();
            }
        });

        btnSave.setOnClickListener(view -> {

            if (view.isEnabled()) {
                saveNews();
                navigateBack(view);
            }
        });
    }

    private void navigateBack(@NonNull View view) {
        Navigation.findNavController(view).navigate(R.id.action_news_details_fragment_to_main_fragment);
    }

    private void populateFieldsOnEdit() {

        if (viewModel.getSelectedNews() == null) {
            return;
        }

        tetTitle.setText(viewModel.getSelectedNews().getTitle());
        tetDescription.setText(viewModel.getSelectedNews().getDescription());
    }

    private void enableSave() {
        btnSave.setEnabled(validateCredentials());
    }

    private boolean validateCredentials() {

        Editable editableTitle = tetTitle.getText();
        Editable editableDescription = tetDescription.getText();

        if (editableTitle == null || editableDescription == null) {
            return false;
        }

        return !editableTitle.toString().trim().isEmpty() && !editableDescription.toString().trim().isEmpty();
    }

    private void saveNews() {

        Editable editableTitle = tetTitle.getText();
        Editable editableDescription = tetDescription.getText();
        if (editableTitle != null && editableDescription != null) {
            String title = editableTitle.toString().trim();
            String description = editableDescription.toString().trim();
            viewModel.saveNews(title, description);
        }
    }

    private void deleteNews() {

        Editable editableTitle = tetTitle.getText();
        if (editableTitle != null && !editableTitle.toString().isEmpty()) {
            editableTitle.clear();
        }

        Editable editableDescription = tetDescription.getText();
        if (editableDescription != null && !editableDescription.toString().isEmpty()) {
            editableDescription.clear();
        }

        if (viewModel.getSelectedNews() != null) {
            viewModel.deleteNews(viewModel.getSelectedNews());
        }
    }

}