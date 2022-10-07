package org.bicell.rest.api.controller;


import io.swagger.annotations.ApiOperation;
import org.bicell.rest.api.dbhelper.VoltDbHelper;
import org.bicell.rest.api.entity.Package;
import org.bicell.rest.api.entity.Subscriber;
import org.bicell.rest.api.repository.PackageRepository;
import org.springframework.web.bind.annotation.*;
import org.voltdb.VoltTable;
import org.voltdb.client.Client;
import org.voltdb.client.ClientResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin //for CORS
@RequestMapping("/package")
public class PackageController {

    PackageRepository packageRepository;

    public PackageController(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @GetMapping("/detail-package-list")
    public List<Package> packageDetailList() throws Exception{
        return packageRepository.packageDetailList();
    }

    /*@GetMapping(value = "/package-info-list")
    public List<Package> packageInfoInList(@RequestBody Subscriber subscriber) throws Exception {
        return packageRepository.getPackageByMSISDNinList(subscriber.getMsisdn());
    }*/


}
