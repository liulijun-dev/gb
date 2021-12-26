package com.guwave.onetest.bff.interfaces;

import com.guwave.onetest.common.BaseResponse;
import com.guwave.onetest.common.dto.IdOnlyDto;
import com.guwave.onetest.instrument.application.InstrumentAppService;
import com.guwave.onetest.instrument.application.dto.InstrumentDetailDto;
import com.guwave.onetest.site.application.dto.AddConnectionDto;
import com.guwave.onetest.site.application.dto.SiteDetailInfoDto;
import com.guwave.onetest.site.application.dto.SiteSimpleInfoDto;
import com.guwave.onetest.site.application.service.SiteAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/site/v1/")
public class SiteApi {
    private final SiteAppService siteAppService;
    private final InstrumentAppService instrumentAppService;

    @PostMapping("sites")
    public BaseResponse<IdOnlyDto<String>> createSite() {
        String siteId = siteAppService.createSite();
        return BaseResponse.success(IdOnlyDto.instance(siteId));
    }

    @DeleteMapping("sites/{siteId}")
    public BaseResponse<Void> deleteSite(@PathVariable("siteId") String siteId) {
        siteAppService.deleteSite(siteId);
        return BaseResponse.success();
    }

    @PostMapping("sites/connections")
    public BaseResponse<IdOnlyDto<String>> addConnection(@RequestBody @Validated AddConnectionDto dto) {
        String connectionId = siteAppService.addConnection(dto);

        // check instrument exist
        InstrumentDetailDto instrumentDto = instrumentAppService.getInstrumentDetail(dto.getInstrumentId());

        // check instrument channel
        instrumentDto.checkChannel(dto.getChannel());

        return BaseResponse.success(IdOnlyDto.instance(connectionId));
    }

    @DeleteMapping("sites/{siteId}/connections/{connectionId}")
    public BaseResponse<Void> deleteConnection(@PathVariable("siteId") String siteId, @PathVariable("connectionId") String connectionId) {
        siteAppService.deleteConnection(siteId, connectionId);
        return BaseResponse.success();
    }

    @GetMapping("sites/{siteId}")
    public BaseResponse<SiteDetailInfoDto> getSiteDetailInfo(@PathVariable("siteId") String siteId) {
        return BaseResponse.success(siteAppService.getSiteDetail(siteId));
    }

    @GetMapping("sites")
    public BaseResponse<List<SiteSimpleInfoDto>> getAllSitesSimpleInfo() {
        return BaseResponse.success(siteAppService.getAllSites());
    }
}