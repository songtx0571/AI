package com.howei.controller;

import com.howei.config.Sender;
import com.howei.pojo.OperationRecord;
import com.howei.service.UserService;
import com.howei.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/ai/operation")
public class OperationController {
    @Autowired
    private Sender sender;


    @PostMapping("/send")
    @ResponseBody
    public String sendRecord(
            @RequestParam(required = false) String verb,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) String sendName
    ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Long timeMillis = System.currentTimeMillis();
        OperationRecord record = new OperationRecord();
        record.setSendName(sendName);
        record.setVerb(verb);
        record.setContent(content);
        record.setType(type);
        record.setDepartmentId(departmentId);
        record.setLongTime(timeMillis.toString());
        record.setCreateTime(sdf.format(timeMillis));
        Long confirmTimeMills = DateFormat.getConfirmTimeMills(timeMillis, "5");
        record.setConfirmTime(sdf.format(confirmTimeMills));
        System.out.println("Openation::" + record);
        try {
            sender.send(record);
            //webSocketOperation.sendMessageToAll(record.toString());
            return "SUCCESSS";
        } catch (Exception e) {
            return "FALSE";
        }

    }
}
