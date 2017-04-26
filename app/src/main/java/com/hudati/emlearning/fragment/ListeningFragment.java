package com.hudati.emlearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.activity.ListeningActivity;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.custom.PracticeEditText;
import com.hudati.emlearning.custom.PracticeSpinner;
import com.hudati.emlearning.custom.PracticeTextView;
import com.hudati.emlearning.model.Question;
import com.hudati.emlearning.model.Sentence;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import butterknife.BindView;

import static com.hudati.emlearning.util.Utils.FRAGMENT_KEY_PAGE;

/**
 * Created by huylv on 07-Apr-17.
 */

public class ListeningFragment extends BaseFragment {
    ArrayList<Sentence> sentences;
    @BindView(R.id.practice_content)
    LinearLayout practice_content;
    String space = " ";
    ListeningActivity listeningActivity;

    int pageNumber;

    public ListeningFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listeningActivity = (ListeningActivity) getActivity();
//        Utils.showInfoDialog(getActivity(),section.getQuestions().getQuestionOptions());
        pageNumber = getArguments().getInt(FRAGMENT_KEY_PAGE);
//        if (section == null) {
//            Log.e("cxz",pageNumber+"section null");
//            return;
//        }
//        if (section.getQuestions() == null) {
//            Log.e("cxz",pageNumber+"section question null");
//            return;
//        }
//        if (section.getQuestions().size() == 0) {
//            Log.e("cxz",pageNumber+"section size 0");
//            return;
//        }
//        ((ListeningActivity) activity).setActionbarTitle(section.getSectionTitle());

        if (listeningActivity.sections.get(pageNumber).getQuestions() == null) {
            Log.e("cxz", pageNumber + "getquestion null");
            return;
        }
        for (Question q : listeningActivity.sections.get(pageNumber).getQuestions()) {
            printQuestion(q);
        }
//        Log.e("Cxz","section:")

    }

    private void printQuestion(Question question) {
        Log.e("cxz", question.toString());
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
                if (word.startsWith("[") && word.endsWith("]")) {
                    //determine edit text or checkbox, spinner, ...
                    switch (question.getQuestionType()) {
                        case InputText:
                            //dien vao cho trong
                            PracticeEditText et = new PracticeEditText(activity);
                            flowLayout.addView(et);
                            break;
                        case MultiChoice:
                            //select 2 answer
                            CheckBox cb = new CheckBox(activity);
                            flowLayout.addView(cb);
                            break;
                        case SingleChoice:

                            break;
                        case Dropdown:
                            PracticeSpinner sp = new PracticeSpinner(activity);
                            String temp = word.split(":")[1];
                            temp = temp.substring(0,temp.length()-1);
                            String[] charr = temp.split(",");
//                            String[] charr = new String[]{"A","B","V"};
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                    activity, android.R.layout.simple_spinner_dropdown_item,charr);
                            sp.setAdapter(adapter);
                            flowLayout.addView(sp);
                            break;
                        default:
                            Log.e("cxz","errorr 3");
                    }
                } else {
                    PracticeTextView tv = new PracticeTextView(activity, word + space);
                    flowLayout.addView(tv);
                }

            }
            practice_content_question.addView(flowLayout);
        }

        practice_content.addView(practice_content_question);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listening;
    }


}
