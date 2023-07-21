package org.dev.webclient.controller;

import lombok.extern.log4j.Log4j2;
import org.dev.webclient.domain.Motion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/model")
@Log4j2
public class ModelController {

    private static final List<Motion> motions = List.of(
            new Motion(5,"활차운동","/filepath/frozenShoulder/5",4),
            new Motion(25,"와위 고관절 외전","/filepath/kneeJoint/25",6),
            new Motion(33,"다리직거상운동","/filepath/Acl/33",12),
            new Motion(9,"능동 외전","/filepath/frozenShoulder/9",8),
            new Motion(22,"무릎 굴곡/신전 (누운)\t","/filepath/kneeJoint/22",8)

    );

    @RequestMapping ("/getMotionData")
    public List<Motion> getData(@RequestParam String programName) {
        log.info("----- Model api에서 data 호출 -----");
        return motions.stream()
                .filter(m -> m.getProgramName().contains(programName))
                .collect(Collectors.toList());
    }

    @RequestMapping("/connect")
    public String checkConnectModel() throws InterruptedException {
        Thread.sleep(100);  // 0.1 seconds
        log.info("----- Model api");
        return "Connected ModelServer!";
    }

}
