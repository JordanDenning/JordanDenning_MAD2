//
//  WineList.swift
//  ProjectPrototype
//
//  Created by Jordan Denning on 2/25/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation

struct WineList: Decodable {
    let name: String
    let region: String
    let winery: String
    let varietal: String
    let price: String
    let vintage: String
}

struct WineData: Decodable {
    var wines = [WineList]()
}


