package com.radicallabsinc.pakarhero.ui.main.dashboard;

public class ViewType {

    private int dataIndex;
    private int type;

    public ViewType(int dataIndex, int type) {
        this.dataIndex = dataIndex;
        this.type = type;
    }

    public int getDataIndex() {
        return dataIndex;
    }

    public int getType() {
        return type;
    }
}
