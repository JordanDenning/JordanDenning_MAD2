//
//  WineList.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/8/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation

struct WineList: Codable {
    let name: String
    let region: String?
    let winery: String?
    let varietal: String?
    let price: String?
    let vintage: String?
}

struct WineData: Decodable {
    var wines = [WineList]()
}
