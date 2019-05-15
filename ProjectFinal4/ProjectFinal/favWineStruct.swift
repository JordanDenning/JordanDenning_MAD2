//
//  favWineStruct.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/9/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation
import Firebase

struct favWine {
    var id: String
    var name: String
    var price: String
    var region: String
    var varietal: String
    var vintage: String
    var winery: String
    
    init(id: String, name: String, region: String, winery: String, varietal: String, price: String, vintage: String) {
        self.id = id
        self.name = name
        self.price = price
        self.region = region
        self.varietal = varietal
        self.vintage = vintage
        self.winery = winery
        
        
        
    }
}

