package ru.sysout.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sysout.model.Hits;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HitsDto {
    private long id;
    private long count;

    public static HitsDto fromHits(Hits hits){
        return new HitsDto(hits.getId(), hits.getCount());
    }
}
