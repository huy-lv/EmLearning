package com.hudati.emlearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.ReadingQuestionResponse;
import com.hudati.emlearning.api.ReadingSection;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.custom.PracticeEditText;
import com.hudati.emlearning.custom.PracticeTextView;
import com.hudati.emlearning.model.ReadingQuestion;
import com.hudati.emlearning.model.Sentence;
import com.hudati.emlearning.util.Utils;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 13-Apr-17.
 */

public class ReadingFragment extends BaseFragment {
    @BindView(R.id.reading_wv)
    WebView reading_wv;
    @BindView(R.id.reading_question_pb)
    ProgressBar reading_question_pb;
    @BindView(R.id.reading_question_rl)
    RelativeLayout reading_question_rl;
    @BindView(R.id.reading_question_content)
    LinearLayout reading_question_content;
    private ReadingSection readingSection;
    private ArrayList<Sentence> sentences;
    private String space = " ";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reading_wv.loadDataWithBaseURL(null, readingSection.getSectionContent(), "text/html", "utf-8", null);

        APIClient.getInterface().loadReadingAnswer(readingSection.getAction().getActionQuestions()).enqueue(new Callback<ReadingQuestionResponse>() {
            @Override
            public void onResponse(Call<ReadingQuestionResponse> call, Response<ReadingQuestionResponse> response) {
                for (ReadingQuestion question : response.body().getData()) {
                    printQuestion(question);
                }
                reading_question_pb.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ReadingQuestionResponse> call, Throwable t) {
                Utils.showInfoDialog(getActivity(),t.getMessage());
            }
        });
    }

    private void printQuestion(ReadingQuestion question) {

        LinearLayout practice_content_question = (LinearLayout) LayoutInflater.from(activity).inflate(R.layout.layout_question, null);
        TextView practice_description = (TextView) practice_content_question.findViewById(R.id.question_description);
        TextView practice_title = (TextView) practice_content_question.findViewById(R.id.question_title);
        WebView question_description_wv = (WebView) practice_content_question.findViewById(R.id.question_description_wv);

        practice_description.setVisibility(View.GONE);
        question_description_wv.setVisibility(View.VISIBLE);
        question_description_wv.loadDataWithBaseURL(null, question.getQuestionSubTitle(), "text/html", "utf-8", null);
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

        ///add view                                                             //TRUE,FALSE,NOT_GIVEN]
        for (Sentence sentence : sentences) {
            Log.e("cxz", "sentence question type :" + question.getQuestionType());
            FlowLayout flowLayout = new FlowLayout(activity);
            for (String word : sentence.words) {
                if (word.startsWith("[") && word.endsWith("]") ) {
                    switch (question.getQuestionType()){
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
                            Spinner sp = new Spinner(activity);
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

        reading_question_content.addView(practice_content_question);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reading;
    }

    public void setSection(ReadingSection readingSection) {
        this.readingSection = readingSection;
    }

}
