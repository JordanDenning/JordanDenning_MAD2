//
//  Teams.swift
//  Lab2
//
//  Created by Jordan Denning on 2/13/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation

struct TeamsDataModel : Codable{
    var team : String
    var player :[String]
}

class TeamsDataModelController  {
    var allData = [TeamsDataModel]()
    let fileName = "teams"
    let datafilename = "data.plist"
    
    func getDataFile(datafile: String) -> URL {
        //get path for data file
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0] //documents directory
        print(docDir)
        
        // URL for our plist
        return docDir.appendingPathComponent(datafile)
    }
    
    func loadData(){
        let pathURL:URL?
        
        // URL for our plist
        let dataFileURL = getDataFile(datafile: datafilename)
        print(dataFileURL)
        
        //if the data file exists, use it
        if FileManager.default.fileExists(atPath: dataFileURL.path){
            pathURL = dataFileURL
        }
        else {
            // URL for our plist
            pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist")
        }
        
        //creates a property list decoder object
        let plistdecoder = PropertyListDecoder()
        do {
            let data = try Data(contentsOf: pathURL!)
            //decodes the property list
            allData = try plistdecoder.decode([TeamsDataModel].self, from: data)
        } catch {
            // handle error
            print(error)
        }
    }
    
    func getTeam() -> [String] {
        var teams = [String]()
        for item in allData{
            teams.append(item.team)
        }
        return teams
    }
    
    func getPlayers(index:Int)-> [String] {
        return allData[index].player
        
    }
    
    func addPlayer(index:Int, newPlayer: String, newIndex: Int){
        allData[index].player.insert(newPlayer, at: newIndex)
    }
    
    func deletePlayer(index:Int, playerIndex: Int)
    {
        allData[index].player.remove(at: playerIndex)
    }
    
    func writeData(){
        // URL for our plist
        let dataFileURL = getDataFile(datafile: datafilename)
        print(dataFileURL)
        //creates a property list decoder object
        let plistencoder = PropertyListEncoder()
        plistencoder.outputFormat = .xml
        do {
            let data = try plistencoder.encode(allData.self)
            try data.write(to: dataFileURL)
        } catch {
            // handle error
            print(error)
        }
    }
}
