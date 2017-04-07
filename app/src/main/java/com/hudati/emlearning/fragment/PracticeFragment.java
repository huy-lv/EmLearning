package com.hudati.emlearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.model.Section;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by huylv on 07-Apr-17.
 */

public class PracticeFragment extends BaseFragment {

    Section section;
    @BindView(R.id.test_content)
    TextView test_content;
    ArrayList<Sentence> sentences;


    public PracticeFragment() {
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Utils.showInfoDialog(getActivity(),section.getQuestion().getQuestionOptions());
        sentences = new ArrayList<>();

        char split = '[';
        if (section != null) {
            Log.e("cxz", "ko null ");
//            test_content.setText();
            StringBuilder builder = new StringBuilder(section.getQuestion().getQuestionOptions());
            int index = 0;
            int sentenceIndex = 0;
            int currentStartIndex = 0;
            Sentence c = new Sentence();
            while (index < builder.length()) {
                if (builder.charAt(index) == split) {
                    c.phrases.add(builder.substring(currentStartIndex, index));

                }
                index++;
            }
        } else {
            Log.e("cxz", "null ");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_practice;
    }

    class Sentence {
        ArrayList<String> phrases = new ArrayList<>();
        ArrayList<TextView> textViews = new ArrayList<>();
        ArrayList<EditText> editTexts = new ArrayList<>();

        Sentence() {

        }
    }
}
