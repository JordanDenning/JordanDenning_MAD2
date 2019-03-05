//
//  dayData.swift
//  NameDay
//
//  Created by Jordan Denning on 3/4/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation

struct dayList: Decodable {
    let name: String
    let day: Int
    let month: Int
}
struct NameData: Decodable{
    var results = [dayList]()
}
