package com.hudati.emlearning.model;

/**
 * Created by huylv on 04-Apr-17.
 */

public abstract class BaseItem {
        public long id;
        public String text;

        public BaseItem(long id, String text) {
            this.id = id;
            this.text = text;
        }
}
