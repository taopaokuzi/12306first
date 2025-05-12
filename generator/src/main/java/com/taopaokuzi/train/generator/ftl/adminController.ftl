package com.taopaokuzi.train.${module}.controller.admin;

import com.taopaokuzi.train.common.context.LoginMemberContext;
import com.taopaokuzi.train.common.resp.CommonResp;
import com.taopaokuzi.train.common.resp.PageResp;
import com.taopaokuzi.train.${module}.req.${Domain}QueryReq;
import com.taopaokuzi.train.${module}.req.${Domain}SaveReq;
import com.taopaokuzi.train.${module}.resp.${Domain}QueryResp;
import com.taopaokuzi.train.${module}.service.${Domain}Service;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/${do_main}")
public class ${Domain}AdminController {

    @Resource
    private ${Domain}Service ${domain}Service;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody ${Domain}SaveReq req) {
        ${domain}Service.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<${Domain}QueryResp>> queryList(@Valid ${Domain}QueryReq req) {
        PageResp<${Domain}QueryResp> list = ${domain}Service.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        ${domain}Service.delete(id);
        return new CommonResp<>();
    }

}
