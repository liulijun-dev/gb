package com.guwave.onetest.bff.interfaces;

public class SiteApi {
    /*@Override
    public PinGroupDetailDto pinGroupDetail(String pinGroupId) {
        PinGroup pinGroup =
            pinGroupRepository.findOne(pinGroupId).orElseThrow(() -> new PinCannotFoundException("Cannot find pin group " + pinGroupId));
        List<Pin> pins = pinRepository.findBatch(pinGroup.pins());

        List<PinDetailDto> pinDetails = new ArrayList<>();
        for (Pin pin : pins) {
            pinDetails.add(new PinDetailDto(pin.id(), pin.getName()));
        }
        PinGroupDetailDto pinGroupDetail = new PinGroupDetailDto(pinGroup.id(),pinGroup.name(),pinDetails);

        return pinGroupDetail;
    }

    @Override
    public String createPinGroup(String name) {
        return pinGroupAppService.createPinGroup(name);
    }*/
}
