package com.mypro.modules.auth.controller;

import com.mypro.common.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class SysRedictController {

    /**
     * 沒有权限
     * @return
     */
    @RequestMapping("/403")
    public Result<?> noAuth(){
        return Result.error("No Auth");
    }
}
