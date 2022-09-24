/*
 * Copyright (c) 2022. Mateusz Tchorek. All rights reserved.
 */

package com.tchorek.dictionary.record;

import java.util.List;
import java.util.stream.Collectors;

public class RecordMapper {
    public static List<RecordModel> mapToModels(List<RecordEntity> list){
        return list.stream().map(object -> new RecordModel(object.getWord(), object.getTranslation(), object.getLanguage(), object.getUser())).collect(Collectors.toList());
    }

}
