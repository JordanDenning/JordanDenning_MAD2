//
//  ThirdViewController.swift
//  Lab1
//
//  Created by Jordan Denning on 2/2/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

//https://stackoverflow.com/questions/43960065/play-specific-sound-when-specific-button-is-pressed-in-ios
//used this to help me with the audio framework

import UIKit
import AVFoundation
class ThirdViewController: UIViewController, AVAudioPlayerDelegate {
    var player:AVAudioPlayer = AVAudioPlayer()
    
    @IBAction func recycleButton(_ sender: UIButton) {
        playSound(named: "goodSound")
    }
    @IBAction func trashButton(_ sender: UIButton) {
        playSound(named: "badSound")
    }

    
    @discardableResult func playSound(named soundName: String) -> AVAudioPlayer {
        
        
        let audioPath = Bundle.main.path(forResource: soundName, ofType: "wav")
        player = try! AVAudioPlayer(contentsOf: NSURL(fileURLWithPath: audioPath!) as URL)
        player.play()
        return player
    }

    


    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
