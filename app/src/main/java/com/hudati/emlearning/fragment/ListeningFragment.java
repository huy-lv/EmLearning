package com.hudati.emlearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.custom.PracticeEditText;
import com.hudati.emlearning.custom.PracticeTextView;
import com.hudati.emlearning.model.Question;
import com.hudati.emlearning.model.Section;
import com.hudati.emlearning.model.Sentence;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import butterknife.BindView;

import static com.hudati.emlearning.util.Utils.FRAGMENT_KEY_PAGE;

/**
 * Created by huylv on 07-Apr-17.
 */

public class ListeningFragment extends BaseFragment {
    Section section;
    ArrayList<Sentence> sentences;
    @BindView(R.id.practice_content)
    LinearLayout practice_content;
    String space = " ";

    int pageNumber;

    public ListeningFragment() {
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        Utils.showInfoDialog(getActivity(),section.getQuestions().getQuestionOptions());
        pageNumber = getArguments().getInt(FRAGMENT_KEY_PAGE);
        if (section == null) {
            return;
        }
        if (section.getQuestions() == null) {
            return;
        }
        if (section.getQuestions().size() == 0) {
            return;
        }
//        ((ListeningActivity) activity).setActionbarTitle(section.getSectionTitle());

        for (Question q : section.getQuestions()) {
            printQuestion(q);
        }
//        Log.e("Cxz","section:")

    }

    private void printQuestion(Question question) {

        LinearLayout practice_content_question = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.layout_question, null);
        TextView practice_description = (TextView) practice_content_question.findViewById(R.id.question_description);
        TextView practice_title = (TextView) practice_content_question.findViewById(R.id.question_title);

        practice_description.setText(question.getQuestionSubTitle());
        practice_title.setText(question.getQuestionTitle());
        //process string
        sentences = new ArrayList<>();
        String source1 = question.getQuestionOptions();

        String[] rawSentence = source1.split("\\n");
        for (String r : rawSentence) {
            String[] words = r.split(space);
            Sentence s = new Sentence();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                s.words.add(word);
            }
            sentences.add(s);
        }

        ///add view
        for (Sentence sentence : sentences) {
            FlowLayout flowLayout = new FlowLayout(activity);
            for (String word : sentence.words) {
                if (word.startsWith("[") && word.endsWith("]") && word.contains("...")) {
                    PracticeEditText et = new PracticeEditText(activity);
                    flowLayout.addView(et);
                } else if (word.startsWith("[") && word.endsWith("]") && word.contains(":")) {
                    CheckBox cb = new CheckBox(activity);
                    flowLayout.addView(cb);
                } else {
                    PracticeTextView tv = new PracticeTextView(activity, word + space);
                    flowLayout.addView(tv);
                }

            }
            practice_content_question.addView(flowLayout);
        }

        practice_content.addView(practice_content_question);
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listening;
    }

}
