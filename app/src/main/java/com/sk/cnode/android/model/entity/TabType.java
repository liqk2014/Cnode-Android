package com.sk.cnode.android.model.entity;

public enum TabType {

    all(com.sk.cnode.android.R.string.tab_all),

    good(com.sk.cnode.android.R.string.tab_good),

    share(com.sk.cnode.android.R.string.tab_share),

    ask(com.sk.cnode.android.R.string.tab_ask),

    job(com.sk.cnode.android.R.string.tab_job);

    private int nameId;

    TabType(int nameId) {
        this.nameId = nameId;
    }

    public int getNameId() {
        return nameId;
    }

}
