package com.project.togather.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.togather.ChatActivity;
import com.project.togather.LoginActivity;
import com.project.togather.community.CommunityActivity;
import com.project.togather.CreatePostActivity;
import com.project.togather.NotificationActivity;
import com.project.togather.ProfileActivity;
import com.project.togather.R;
import com.project.togather.databinding.ActivityHomeBinding;
import com.project.togather.toast.ToastWarning;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new RecyclerViewAdapter();

        ArrayList<PostInfoItem> postInfoItems = new ArrayList<>();

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(HomeActivity.this, HomePostDetailActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnLongItemClickListener(new RecyclerViewAdapter.OnLongItemClickListener() {
            @Override
            public void onLongItemClick(int pos) {
                Intent intent = new Intent(HomeActivity.this, HomePostDetailActivity.class);
                startActivity(intent);
            }
        });

        // initiate recyclerview
        binding.postRecyclerView.setAdapter(adapter);
        binding.postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.postRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        // Adapter 안에 아이템의 정보 담기 (하드 코딩)
        postInfoItems.add(new PostInfoItem("https://cdn.mkhealth.co.kr/news/photo/202306/64253_68458_1153.png", "개신동 교촌치킨 파티 구함", "chicken", 320, 3, 2, false, 1));
        postInfoItems.add(new PostInfoItem("https://cdn.dominos.co.kr/admin/upload/goods/20240214_8rBc1T61.jpg?RS=350x350&SP=1", "도미노 피자 드실분 구해요", "pizza", 160, 2, 0, false, 0));
        postInfoItems.add(new PostInfoItem("https://mblogthumb-phinf.pstatic.net/MjAyMjA3MjhfMTY5/MDAxNjU4OTkyODg0NTA3.z8WzaZAOKBvo4JkSm9lTMOTiNsKEUNHZJYRB-DPZCdEg.0WdqohiJPsSM5pXWYl-HvTE3JUVlUPe7LT-U6wvjUQwg.JPEG.duwlsrjdwb/KakaoTalk_20220728_151114228_10.jpg?type=w800", "사창동 우리집 닭강정 파티!!", " chicken ", 500, 1, 0, false, 0));
        postInfoItems.add(new PostInfoItem("https://image.kmib.co.kr/online_image/2024/0131/2024013114261427977_1706678775_0019120339.jpg", "맘스터치 배달 파티 999~~", "hamburger", 600, 3, 3, true, 2));
        postInfoItems.add(new PostInfoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6LTXILpqDk2KY425YAGSIAdF84ogxh-OFRz2P51EPvA&s", "행컵 그룹 구해용", "korean_food", 550, 2, 1, false, 0));
        postInfoItems.add(new PostInfoItem("", "짚신 스시 & 롤 배달 구해요", "japanese_food", 555, 1, 0, true, 1));
        postInfoItems.add(new PostInfoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj0BYs-iE7kn3fmdg0eVBxtqO89kwVRBFe_3Y8uZrgMA&s", "대장집 파티 구", "chinese_food", 560, 2, 1, false, 0));
        postInfoItems.add(new PostInfoItem("https://d12zq4w4guyljn.cloudfront.net/750_750_20201122041810_photo1_5831aaf849cf.jpg", "파브리카 배달 구해용", "western_food", 700, 2, 2, false, 1));
        postInfoItems.add(new PostInfoItem("https://media-cdn.tripadvisor.com/media/photo-s/12/31/92/d9/1519804025288-largejpg.jpg", "신전 떡볶이 구해유", "snack", 900, 3, 2, false, 2));
        postInfoItems.add(new PostInfoItem("https://d12zq4w4guyljn.cloudfront.net/750_750_20230517093845_photo1_edd2f5913a1b.jpg", "메가커피 999", "cafe_and_dessert", 1000, 1, 0, false, 2));
        postInfoItems.add(new PostInfoItem("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-1FF9Hpe-_ERtrBHcUDeeckMOeOzm6IWylD_mJJlJEQ&s", "컴포즈 배달 구해요!!!", "cafe_and_dessert", 1500, 1, 0, false, 1));

        adapter.setPostInfoList(postInfoItems);

        /** "알림" 버튼 클릭 시 */
        binding.notificationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        /** "신규 알림" 버튼 클릭 시 */
        binding.notificationNewImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        /** "전체" 탭 버튼 클릭 시 */
        binding.allFoodTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.allFoodTabButton.setTypeface(null, Typeface.BOLD);
                binding.allFoodTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.allFoodTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "치킨" 탭 버튼 클릭 시 */
        binding.chickenTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.chickenTabButton.setTypeface(null, Typeface.BOLD);
                binding.chickenTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.chickenTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "피자" 탭 버튼 클릭 시 */
        binding.pizzaTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.pizzaTabButton.setTypeface(null, Typeface.BOLD);
                binding.pizzaTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.pizzaTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "햄버거" 탭 버튼 클릭 시 */
        binding.hamburgerTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.hamburgerTabButton.setTypeface(null, Typeface.BOLD);
                binding.hamburgerTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.hamburgerTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "한식" 탭 버튼 클릭 시 */
        binding.koreanFoodTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.koreanFoodTabButton.setTypeface(null, Typeface.BOLD);
                binding.koreanFoodTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.koreanFoodTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "일식" 탭 버튼 클릭 시 */
        binding.japaneseFoodTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.japaneseFoodTabButton.setTypeface(null, Typeface.BOLD);
                binding.japaneseFoodTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.japaneseFoodTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "중식" 탭 버튼 클릭 시 */
        binding.chineseFoodTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.chineseFoodTabButton.setTypeface(null, Typeface.BOLD);
                binding.chineseFoodTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.chineseFoodTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "양식" 탭 버튼 클릭 시 */
        binding.westernFoodTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.westernFoodTabButton.setTypeface(null, Typeface.BOLD);
                binding.westernFoodTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.westernFoodTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "분식" 탭 버튼 클릭 시 */
        binding.snackTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.snackTabButton.setTypeface(null, Typeface.BOLD);
                binding.snackTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.snackTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "카페·디저트" 탭 버튼 클릭 시 */
        binding.cafeAndDessertTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allTabStyleClear();
                binding.cafeAndDessertTabButton.setTypeface(null, Typeface.BOLD);
                binding.cafeAndDessertTabButton.setTextColor(getResources().getColor(R.color.text_color));
                binding.cafeAndDessertTabButton.setBackground(getResources().getDrawable(R.drawable.selected_category_tab_border_bottom));
            }
        });

        /** "동네생활" 레이아웃 클릭 시 */
        binding.communityActivityRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });

        /** "글 쓰기" 레이아웃 클릭 시 */
        binding.createPostActivityRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CreatePostActivity.class);
                startActivity(intent);
            }
        });

        /** "채팅" 레이아웃 클릭 시 */
        binding.chatActivityRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        /** "내 정보" 레이아웃 클릭 시 */
        binding.profileActivityRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    public static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private ArrayList<PostInfoItem> items = new ArrayList<>();

        public interface OnItemClickListener {
            void onItemClick(int pos);
        }

        private OnItemClickListener onItemClickListener = null;

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.onItemClickListener = listener;
        }


        public interface OnLongItemClickListener {
            void onLongItemClick(int pos);
        }

        private OnLongItemClickListener onLongItemClickListener = null;

        public void setOnLongItemClickListener(OnLongItemClickListener listener) {
            this.onLongItemClickListener = listener;
        }

        @NonNull
        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_view_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
            holder.onBind(items.get(position));
        }

        public void setPostInfoList(ArrayList<PostInfoItem> list) {
            this.items = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout root_relativeLayout;
            RelativeLayout content_relativeLayout;

            ImageView post_imageView;
            ImageView currentPartyMemberNumFirstState_imageView;
            ImageView currentPartyMemberSecondState_imageView;
            ImageView currentPartyMemberNumThirdState_imageView;
            ImageView liked_imageView;

            TextView postTitle_textView;
            TextView category_textView;
            TextView elapsedTime_textView;
            TextView recruitmentComplete_textView;
            TextView likedCnt_textView;

            CardView currentPartyMemberNumFirstState_cardView;
            CardView currentPartyMemberNumSecondState_cardView;
            CardView currentPartyMemberNumThirdState_cardView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            if (onItemClickListener != null) {
                                onItemClickListener.onItemClick(position);

                                // 빨간색 배경으로 변경
                                root_relativeLayout.setBackgroundColor(itemView.getResources().getColor(R.color.post_clicked_gray_color));
                                content_relativeLayout.setBackgroundColor(itemView.getResources().getColor(R.color.post_clicked_gray_color));

                                // 500 밀리초(0.5초) 후에 이전 배경색으로 변경
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 이전 배경색으로 변경
                                        root_relativeLayout.setBackground(itemView.getResources().getDrawable(R.drawable.list_item_view_border_bottom_white_background));
                                        content_relativeLayout.setBackground(itemView.getResources().getDrawable(R.drawable.list_item_view_border_bottom_white_background));
                                    }
                                }, 500); // 0.5초 지연
                            }
                        }
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            if (onLongItemClickListener != null) {
                                onLongItemClickListener.onLongItemClick(position);
                                return true;
                            }
                        }
                        return false;
                    }
                });

                root_relativeLayout = itemView.findViewById(R.id.root_relativeLayout);
                content_relativeLayout = itemView.findViewById(R.id.content_relativeLayout);

                post_imageView = itemView.findViewById(R.id.post_imageView);

                postTitle_textView = itemView.findViewById(R.id.postTitle_textView);
                category_textView = itemView.findViewById(R.id.category_textView);
                elapsedTime_textView = itemView.findViewById(R.id.elapsedTime_textView);
                recruitmentComplete_textView = itemView.findViewById(R.id.recruitmentComplete_textView);

                currentPartyMemberNumFirstState_cardView = itemView.findViewById(R.id.currentPartyMemberNumFirstState_cardView);
                currentPartyMemberNumSecondState_cardView = itemView.findViewById(R.id.currentPartyMemberNumSecondState_cardView);
                currentPartyMemberNumThirdState_cardView = itemView.findViewById(R.id.currentPartyMemberNumThirdState_cardView);

                currentPartyMemberNumFirstState_imageView = itemView.findViewById(R.id.currentPartyMemberNumFirstState_imageView);
                currentPartyMemberSecondState_imageView = itemView.findViewById(R.id.currentPartyMemberSecondState_imageView);
                currentPartyMemberNumThirdState_imageView = itemView.findViewById(R.id.currentPartyMemberNumThirdState_imageView);

                liked_imageView = itemView.findViewById(R.id.liked_imageView);

                likedCnt_textView = itemView.findViewById(R.id.likedCnt_textView);

                post_imageView.setImageResource(R.drawable.home_temp_image_1);
            }

            void onBind(PostInfoItem item) {
                if (item.getPostThumbnailImageUrl().equals("")) {
                    post_imageView.setImageResource(R.drawable.post_thumbnail_background_logo);
                } else {
                    Glide.with(itemView)
                            .load(item.getPostThumbnailImageUrl()) // 이미지 URL 가져오기
                            .placeholder(R.drawable.one_person_logo) // 로딩 중에 표시할 이미지
                            .error(R.drawable.one_person_logo) // 에러 발생 시 표시할 이미지
                            .into(post_imageView); // ImageView에 이미지 설정
                }

                postTitle_textView.setText(item.getTitle());
                switch (item.getCategory()) {
                    case "chicken":
                        category_textView.setText("치킨");
                        break;
                    case "pizza":
                        category_textView.setText("피자");
                        break;
                    case "hamburger":
                        category_textView.setText("햄버거");
                        break;
                    case "korean_food":
                        category_textView.setText("한식");
                        break;
                    case "japanese_food":
                        category_textView.setText("일식");
                        break;
                    case "chinese_food":
                        category_textView.setText("중식");
                        break;
                    case "western_food":
                        category_textView.setText("양식");
                        break;
                    case "snack":
                        category_textView.setText("분식");
                        break;
                    case "cafe_and_dessert":
                        category_textView.setText("카페·디저트");
                        break;
                    default:
                        Log.d("로그: ", item.getCategory() + "는 존재하지 않는 카테고리입니다.");
                }

                long elapsedTime = item.getElapsedTime();
                String elapsedTime_str;
                if (elapsedTime < 60) {
                    elapsedTime_str = elapsedTime + "초 전";
                } else if (elapsedTime < 3600) {
                    elapsedTime_str = elapsedTime / 60 + "분 전";
                } else if (elapsedTime < 86400) {
                    elapsedTime_str = elapsedTime / 3600 + "시간 전";
                } else if (elapsedTime < 86400 * 365) {
                    elapsedTime_str = elapsedTime / 86400 + "일 전";
                } else {
                    elapsedTime_str = elapsedTime / 86400 * 365 + "일 전";
                }
                elapsedTime_textView.setText(elapsedTime_str);

                if (item.getMaxPartyMemberNum() == item.getCurrentPartyMemberNum()) {
                    recruitmentComplete_textView.setVisibility(View.VISIBLE);
                } else {
                    currentPartyMemberNumFirstState_cardView.setVisibility(item.getMaxPartyMemberNum() >= 1 ? View.VISIBLE : View.INVISIBLE);
                    currentPartyMemberNumFirstState_imageView.setImageResource(item.getCurrentPartyMemberNum() >= 1 ? R.drawable.one_person_logo_filled : R.drawable.one_person_logo);

                    currentPartyMemberNumSecondState_cardView.setVisibility(item.getMaxPartyMemberNum() >= 2 ? View.VISIBLE : View.INVISIBLE);
                    currentPartyMemberSecondState_imageView.setImageResource(item.getCurrentPartyMemberNum() >= 2 ? R.drawable.one_person_logo_filled : R.drawable.one_person_logo);

                    currentPartyMemberNumThirdState_cardView.setVisibility(item.getMaxPartyMemberNum() >= 3 ? View.VISIBLE : View.INVISIBLE);
                    currentPartyMemberNumThirdState_imageView.setImageResource(item.getCurrentPartyMemberNum() >= 3 ? R.drawable.one_person_logo_filled : R.drawable.one_person_logo);
                }

                liked_imageView.setImageResource(item.getLikedState() ? R.drawable.like_filled : R.drawable.like_normal);
                likedCnt_textView.setText("" + item.getLikedCnt());
            }
        }
    }

    // 음식 카테고리 탭에 설정된 스타일을 제거하는 함수
    void allTabStyleClear() {
        binding.allFoodTabButton.setBackground(null);
        binding.allFoodTabButton.setTypeface(null, Typeface.NORMAL);
        binding.allFoodTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.chickenTabButton.setBackground(null);
        binding.chickenTabButton.setTypeface(null, Typeface.NORMAL);
        binding.chickenTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.pizzaTabButton.setBackground(null);
        binding.pizzaTabButton.setTypeface(null, Typeface.NORMAL);
        binding.pizzaTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.hamburgerTabButton.setBackground(null);
        binding.hamburgerTabButton.setTypeface(null, Typeface.NORMAL);
        binding.hamburgerTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.koreanFoodTabButton.setBackground(null);
        binding.koreanFoodTabButton.setTypeface(null, Typeface.NORMAL);
        binding.koreanFoodTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.japaneseFoodTabButton.setBackground(null);
        binding.japaneseFoodTabButton.setTypeface(null, Typeface.NORMAL);
        binding.japaneseFoodTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.chineseFoodTabButton.setBackground(null);
        binding.chineseFoodTabButton.setTypeface(null, Typeface.NORMAL);
        binding.chineseFoodTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.westernFoodTabButton.setBackground(null);
        binding.westernFoodTabButton.setTypeface(null, Typeface.NORMAL);
        binding.westernFoodTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.snackTabButton.setBackground(null);
        binding.snackTabButton.setTypeface(null, Typeface.NORMAL);
        binding.snackTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));

        binding.cafeAndDessertTabButton.setBackground(null);
        binding.cafeAndDessertTabButton.setTypeface(null, Typeface.NORMAL);
        binding.cafeAndDessertTabButton.setTextColor(getResources().getColor(R.color.not_selected_menu_item_gray_color));
    }
}