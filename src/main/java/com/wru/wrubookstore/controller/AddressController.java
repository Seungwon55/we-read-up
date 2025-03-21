package com.wru.wrubookstore.controller;

import com.wru.wrubookstore.dto.AddressDto;
import com.wru.wrubookstore.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/myPage/addressList")
    public String addressList(Model model) {
        // 임의의 회원 값 입력(수정 필요)
        try {
            List<AddressDto> addressDtoList = addressService.selectList(1);

            model.addAttribute("addressList", addressDtoList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "myPage/address-list";
    }

    @GetMapping("/myPage/addAddress")
    public String addAddressForm() {

        return "myPage/add-address";
    }

    @PostMapping("/myPage/addAddress")
    public String addAddress(@ModelAttribute AddressDto addressDto) {
        // 임의의 회원 값 입력(수정 필요)
        addressDto.setMemberId(1);

        try {
            addressService.insertAddress(addressDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/myPage/addressList";
    }

    @GetMapping("/myPage/editAddress/{addressId}")
    public String updateAddressForm(@PathVariable Integer addressId, Model model) {
        try {
            AddressDto addressDto = addressService.selectOne(addressId);

            model.addAttribute("address", addressDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "myPage/edit-address";
    }

    @PostMapping("/myPage/editAddress/{addressId}")
    public String updateAddress(@ModelAttribute AddressDto addressDto) {
        try {
            // 임의의 회원 값 입력(로그인 구현 후 수정 필요)
            addressDto.setMemberId(1);

            addressService.updateAddress(addressDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/myPage/addressList";
    }
}
