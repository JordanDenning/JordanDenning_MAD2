//
//  favWineDataController.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/9/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation
import Firebase

class favWineController {
    var ref: DatabaseReference!

    var favWineData = [favWine]()

    var onDataUpdate: ((_ data: [favWine]) -> Void)?

    func setupFirebaseListener(){
//        print("HI")
        ref = Database.database().reference().child("Wine")
//        print(ref)
        //set up a listener for Firebase data change events
        //this event will fire with the initial data and then all data changes
        ref.observe(DataEventType.value, with: {snapshot in
            self.favWineData.removeAll()
            //DataSnapshot represents the Firebase data at a given time
            //loop through all the child data nodes
            for snap in snapshot.children.allObjects as! [DataSnapshot]{
                //print(snap)
                let wineID = snap.key
                if let wineDict = snap.value as? [String: String], //get value as a Dictionary
                    
                    let wineName = wineDict["name"],
                    let winePrice = wineDict["price"],
                    let wineVintage = wineDict["vintage"],
                    let wineVarietal = wineDict["varietal"],
                    let wineRegion = wineDict["region"],
                    let wineWinery = wineDict["winery"]
                {
//                    print("HI")
                    let newWine = favWine(id: wineID , name: wineName, region: wineRegion, winery: wineWinery, varietal: wineVarietal, price: winePrice, vintage: wineVintage)
                    //add recipe to recipes array
                    self.favWineData.append(newWine)
//                    print(newWine)
                }
            }
            //passing the results to the onDataUpdate closure
            self.onDataUpdate?(self.favWineData)
        })
    }

    func getWine()->[favWine]{
        return favWineData
    }

    func deleteWine(wineID: String){
        // Delete the object from Firebase
//        print("Trying to delete")
//        print(wineID)
        ref.child(wineID).removeValue()
    }

    func addWine(name: String, region: String, winery: String, varietal: String, price: String, vintage: String){
        //create Dictionary
        let newWineDict = ["name": name, "region": region, "winery": winery, "varietal":varietal, "price":price, "vintage":vintage]
        
//        print(newWineDict["name"]!)
//        print(ref)
        //create a new ID
        let wineRef = ref.childByAutoId()
        
        //write data to the new ID in Firebase
        wineRef.setValue(newWineDict)
    }

}

