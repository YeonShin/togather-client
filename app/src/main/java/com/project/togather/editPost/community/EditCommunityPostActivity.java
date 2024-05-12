package com.project.togather.editPost.community;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.project.togather.MainActivity;
import com.project.togather.R;
import com.project.togather.community.CommunityPostDetailActivity;
import com.project.togather.createPost.community.CreateCommunityPostActivity;
import com.project.togather.databinding.ActivityCommunityPostDetailBinding;
import com.project.togather.databinding.ActivityEditCommunityPostBinding;
import com.project.togather.toast.ToastWarning;
import com.project.togather.utils.TokenManager;

import java.io.InputStream;

public class EditCommunityPostActivity extends AppCompatActivity {

    private ActivityEditCommunityPostBinding binding;
    private TokenManager tokenManager;
    private BottomSheetBehavior selectCategoryBottomSheetBehavior;

    private static final int REQUEST_GALLERY = 2;

    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCommunityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tokenManager = TokenManager.getInstance(this);

        // 토큰 값이 없다면 메인 액티비티로 이동
        if (tokenManager.getToken() == null) {
            startActivity(new Intent(EditCommunityPostActivity.this, MainActivity.class));
            finish();
        }

        // X 이미지뷰 클릭 시 현재 액티비티 종료
        binding.closeActivityImageView.setOnClickListener(view -> finish());

        binding.selectCategoryRelativeLayout.setOnClickListener(view -> {
            hideKeyboard();
            selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });

        selectCategoryBottomSheetBehavior = BottomSheetBehavior.from(

                findViewById(R.id.createCommunityPostSelectCategoryBottomSheet_layout));

        selectCategoryBottomSheetBehavior.setDraggable(false);

        selectCategoryBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        binding.backgroundDimmer.setVisibility(View.VISIBLE);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                    case BottomSheetBehavior.STATE_HIDDEN:
                        binding.backgroundDimmer.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                binding.backgroundDimmer.setAlpha(slideOffset);
                binding.backgroundDimmer.setVisibility(View.VISIBLE);
            }
        });

        // 어두운 배경 클릭 이벤트 설정
        binding.backgroundDimmer.setOnClickListener(view ->

        {
            if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        // 게시글 카테고리 선택
        findViewById(R.id.famousRestaurantCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("맛집");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.famousRestaurantCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.famousRestaurantCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.hospitalAndPharmacyCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("병원/약국");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.hospitalAndPharmacyCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.hospitalAndPharmacyCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.lifeAndConvenienceCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("생활/편의");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.lifeAndConvenienceCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.lifeAndConvenienceCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.concernAndStoryCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("고민/사연");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.concernAndStoryCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.concernAndStoryCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.neighborhoodCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("동네친구");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.neighborhoodCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.neighborhoodCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.exerciseCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("운동");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.exerciseCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.exerciseCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.petCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("반려동물");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.petCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.petCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.beautyCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("미용");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.beautyCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.beautyCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.movingAndConstructionCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("이사/시공");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.movingAndConstructionCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.movingAndConstructionCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.residentialAndEstateCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("주거/부동산");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.residentialAndEstateCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.residentialAndEstateCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.educationCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("교육");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.educationCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.educationCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.hobbyCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("취미");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.hobbyCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.hobbyCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.neighborhoodAccidentCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("동네사건사고");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.neighborhoodAccidentCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.neighborhoodAccidentCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.neighborhoodSceneCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("동네풍경");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.neighborhoodSceneCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.neighborhoodSceneCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.lostAndMissingCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("분실/실종");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.lostAndMissingCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.lostAndMissingCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.pregnancyAndParentingCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("임신/육아");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.pregnancyAndParentingCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.pregnancyAndParentingCategorycheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        findViewById(R.id.generalCategory_button).

                setOnClickListener(view ->

                {
                    if (selectCategoryBottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
                        selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        binding.postCategoryTextView.setText("일반");
                        clearCategoryStyle();
                        ((Button) findViewById(R.id.generalCategory_button)).setTextColor(getResources().getColor(R.color.theme_color));
                        ((ImageView) findViewById(R.id.generalCategoryCheck_imageView)).setVisibility(View.VISIBLE);
                    }
                });

        /** (완료) 버튼 클릭 이벤트 설정 */
        binding.createCommunityPostButton.setOnClickListener(view ->

        {
            hideKeyboard();

            if (binding.postCategoryTextView.getText().toString().equals("게시글의 주제를 선택해 주세요")) {
                selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                return;
            }

            if (binding.contentEditText.getText().toString().length() < 8) {
                new ToastWarning("본문을 8자 이상 작성해 주세요", EditCommunityPostActivity.this);
                return;
            }

            startActivity(new Intent(EditCommunityPostActivity.this, CommunityPostDetailActivity.class));
        });

        /** (제목) 입력란 텍스트 입력 시 */
        binding.postTitleEditText.addTextChangedListener(new

                                                                 TextWatcher() {
                                                                     @Override
                                                                     public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                                                     }

                                                                     @Override
                                                                     public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                     }

                                                                     @Override
                                                                     public void afterTextChanged(Editable s) {
                                                                         if (binding.postTitleEditText.getText().toString().length() > 0 && binding.contentEditText.getText().toString().length() > 0) {
                                                                             binding.createCommunityPostButton.setTextColor(getResources().getColor(R.color.text_color));
                                                                             binding.createCommunityPostButton.setEnabled(true);
                                                                             return;
                                                                         }

                                                                         binding.createCommunityPostButton.setTextColor(getResources().getColor(R.color.gray_text));
                                                                         binding.createCommunityPostButton.setEnabled(false);
                                                                     }
                                                                 });

        /** (본문) 입력란 텍스트 입력 시 */
        binding.contentEditText.addTextChangedListener(new

                                                               TextWatcher() {
                                                                   @Override
                                                                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                                                   }

                                                                   @Override
                                                                   public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                   }

                                                                   @SuppressLint("ResourceAsColor")
                                                                   @Override
                                                                   public void afterTextChanged(Editable s) {
                                                                       if (binding.postTitleEditText.getText().toString().length() > 0 && binding.contentEditText.getText().toString().length() > 0) {
                                                                           binding.createCommunityPostButton.setTextColor(getResources().getColor(R.color.text_color));
                                                                           binding.createCommunityPostButton.setEnabled(true);
                                                                           return;
                                                                       }

                                                                       binding.createCommunityPostButton.setTextColor(getResources().getColor(R.color.gray_text));
                                                                       binding.createCommunityPostButton.setEnabled(false);
                                                                   }
                                                               });

        // (갤러리) 이미지뷰 클릭 이벤트 설정
        findViewById(R.id.gallery_imageView).

                setOnClickListener(view ->

                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, REQUEST_GALLERY);
                });

        // (키보드) 이미지뷰 클릭 이벤트 설정
        findViewById(R.id.hideKeyboard_imageView).

                setOnClickListener(view ->

                {
                    hideKeyboard();
                });

        // 게시글 섬네일 이미지 우측 상단의 X 버튼 클릭 이벤트 설정
        binding.deleteImageCardView.setOnClickListener(view ->

        {
            // 이미지 초기화
            binding.postThumbnailImageView.setImageResource(0);
            binding.postImageRelativeLayout.setVisibility(View.INVISIBLE);
            selectedImageUri = Uri.parse("");
        });

        // 데이터 하드 코딩
        String response_selectedCategory = "운동";
        switch (response_selectedCategory) {
            case "맛집":
                findViewById(R.id.famousRestaurantCategory_button).performClick();
                break;
            case "병원/약국":
                findViewById(R.id.hospitalAndPharmacyCategory_button).performClick();
                break;
            case "생활/편의":
                findViewById(R.id.lifeAndConvenienceCategory_button).performClick();
                break;
            case "고민/사연":
                findViewById(R.id.concernAndStoryCategory_button).performClick();
                break;
            case "동네친구":
                findViewById(R.id.neighborhoodCategory_button).performClick();
                break;
            case "운동":
                findViewById(R.id.exerciseCategory_button).performClick();
                break;
            case "반려동물":
                findViewById(R.id.petCategory_button).performClick();
                break;
            case "미용":
                findViewById(R.id.beautyCategory_button).performClick();
                break;
            case "이사/시공":
                findViewById(R.id.movingAndConstructionCategory_button).performClick();
                break;
            case "주거/부동산":
                findViewById(R.id.residentialAndEstateCategory_button).performClick();
                break;
            case "교육":
                findViewById(R.id.educationCategory_button).performClick();
                break;
            case "취미":
                findViewById(R.id.hobbyCategory_button).performClick();
                break;
            case "동네사건사고":
                findViewById(R.id.neighborhoodAccidentCategory_button).performClick();
                break;
            case "동네풍경":
                findViewById(R.id.neighborhoodSceneCategory_button).performClick();
                break;
            case "분실/실종":
                findViewById(R.id.lostAndMissingCategory_button).performClick();
                break;
            case "임신/육아":
                findViewById(R.id.pregnancyAndParentingCategory_button).performClick();
                break;
            case "일반":
                findViewById(R.id.generalCategory_button).performClick();
                break;
            default:
                Log.d("로그 : ", response_selectedCategory + "는 존재하지 않는 카테고리입니다.");
        }

        String postThumbnailImageUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhTU-XV5ayP7d88yRVYZ-Hlbx8ocT3y4btXHRzdgy9SA&s";
        Glide.with(binding.postThumbnailImageView)
                .load(postThumbnailImageUri) // 이미지 URL 가져오기
                .placeholder(R.drawable.one_person_logo) // 로딩 중에 표시할 이미지
                .error(R.drawable.one_person_logo) // 에러 발생 시 표시할 이미지
                .into(binding.postThumbnailImageView); // ImageView에 이미지 설정
        binding.postThumbnailImageView.setVisibility(postThumbnailImageUri.equals("") ? View.GONE : View.VISIBLE);

        binding.postTitleEditText.setText("제목이랍니다");
        binding.contentEditText.setText("이 부분은 내용이랍니다.");
    }

    void clearCategoryStyle() {
        ((Button) findViewById(R.id.famousRestaurantCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.famousRestaurantCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.hospitalAndPharmacyCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.hospitalAndPharmacyCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.lifeAndConvenienceCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.lifeAndConvenienceCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.concernAndStoryCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.concernAndStoryCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.neighborhoodCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.neighborhoodCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.exerciseCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.exerciseCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.petCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.petCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.beautyCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.beautyCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.movingAndConstructionCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.movingAndConstructionCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.residentialAndEstateCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.residentialAndEstateCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.educationCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.educationCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.hobbyCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.hobbyCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.hobbyCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.hobbyCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.neighborhoodAccidentCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.neighborhoodAccidentCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.neighborhoodSceneCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.neighborhoodSceneCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.lostAndMissingCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.lostAndMissingCategoryCheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.pregnancyAndParentingCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.pregnancyAndParentingCategorycheck_imageView)).setVisibility(View.INVISIBLE);

        ((Button) findViewById(R.id.generalCategory_button)).setTextColor(getResources().getColor(R.color.text_color));
        ((ImageView) findViewById(R.id.generalCategoryCheck_imageView)).setVisibility(View.INVISIBLE);
    }

    /**
     * 키보드를 숨기는 함수
     */
    void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void updateImage(Uri imageUri) {
        try (InputStream inputStream = getContentResolver().openInputStream(imageUri)) {
            selectCategoryBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            Glide.with(binding.postThumbnailImageView)
                    .load(imageUri) // 이미지 URL 가져오기
                    .placeholder(R.drawable.user_default_profile) // 로딩 중에 표시할 이미지
                    .error(R.drawable.user_default_profile) // 에러 발생 시 표시할 이미지
                    .into(binding.postThumbnailImageView); // ImageView에 이미지 설정
            binding.postImageRelativeLayout.setVisibility(View.VISIBLE);
        } catch (
                Exception e) {
            Toast.makeText(this, "이미지를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_GALLERY:
                    // 갤러리에서 이미지를 선택했을 때의 처리
                    Uri selectedImageUri = data.getData();
                    updateImage(selectedImageUri);
                    break;
            }
        }
    }
}