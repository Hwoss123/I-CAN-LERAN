package com.utils;

import com.pojo.Chat_records;

import java.util.Comparator;

public class TimestampComparator implements Comparator<Chat_records> {
    @Override
    public int compare(Chat_records record1, Chat_records record2) {
        return record1.getTimestamp().compareTo(record2.getTimestamp());
    }
}