package com.bramh.pruebaPersonas.Repository;

import com.bramh.pruebaPersonas.Models.Cosa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cosasApi", url = "http://localhost:8081/api/v1", path = "/cosa")
public interface CosaClient {

    @GetMapping("/propietario")
    List<Cosa> obtenerCosasPorPropietario(@RequestParam("propietario") String propietario);
}
