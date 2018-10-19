package com.apap.TP1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.TP1.model.InstansiModel;
import com.apap.TP1.model.JabatanModel;
import com.apap.TP1.service.InstansiService;
import com.apap.TP1.service.JabatanService;
import com.apap.TP1.service.PegawaiService;

@Controller
public class HomeController {

	@Autowired
	private PegawaiService pegawaiService;
	@Autowired
	private JabatanService jabatanService;
	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> getJabatan = jabatanService.getAllJabatan();
		model.addAttribute("Listjabatan", getJabatan);
		
		List<InstansiModel> getInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi", getInstansi);
		
		return "home";
	}
}
