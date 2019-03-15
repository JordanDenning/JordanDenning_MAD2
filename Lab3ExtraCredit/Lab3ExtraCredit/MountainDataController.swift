//
//  MountainDataController.swift
//  Lab3ExtraCredit
//
//  Created by Jordan Denning on 3/15/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation

class MountainDataController {
    var mountainData = [Mountain]()
    let fileName = "14ers"
    
    func loadData(){
        if let pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            //creates a property list decoder object
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                //decodes the property list
                mountainData = try plistdecoder.decode([Mountain].self, from: data)
            } catch {
                // handle error
                print(error)
            }
        }
    }
    
    func getMountain() -> [Mountain]{
        var mountains = [Mountain]()
        for mountain in mountainData{
            mountains.append(mountain)
        }
        return mountains
    }
    
}
