//
//  PlasticUsesController.swift
//  Lab1
//
//  Created by Jordan Denning on 2/2/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation

class PlasticUsesController{
    var allData = [PlasticUses]()
    let fileName = "lab1"
    
    
    func loadData(){
        if let pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist"){
            //creates a property list decoder object
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                //decodes the property list
                allData = try plistdecoder.decode([PlasticUses].self, from: data)
            } catch {
                // handle error
                print(error)
            }
        }
    }
    
    func getPlastic() -> [String]{
        var plasticName = [String]()
        for plastic2 in allData{
            plasticName.append(plastic2.plastic)
        }
        return plasticName
    }
    
    func getUse(index:Int) -> [String] {
        return allData[index].use
    }

}
