package org.dev.webclient.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Motion     {

    private int programId;
    private String programName;
    private String img_path;
    private int num_keyPoints;

    @Override
    public String toString() {
        return "Motion data : " +
                "ProgramId=" + programName +
                ", ProgramName='" + programName + '\'' +
                ", img_path='" + img_path + '\'' +
                ", num_keyPoints=" + num_keyPoints +
                '}';
    }
}
