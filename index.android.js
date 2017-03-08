'use strict';

import React from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  NativeModules,
  Navigator,
  NavigatorIOS,
  TouchableOpacity
} from 'react-native';

import MyScene from './MyScene';
import ListViewExample from './ListViewExample';
// import {Keyboard}  from 'react-native';
// Keyboard.addListener('keyboardWillShow1', (e)=>this.updateKeyboardSpace(e));
var RCTDeviceEventEmitter = require('RCTDeviceEventEmitter');  

//var {NativeModules}=require('react-native');
var rnToastAndroid = NativeModules.RNCommunicationModule;

class HelloWorld extends React.Component {
componentWillMount() {  
        RCTDeviceEventEmitter.addListener('hellowDDD', function(e: Event) {
          alert('123');
    // handle event.
  });
    // this.addListenerOn(RCTDeviceEventEmitter,  
    //                    'keyboardWillShow',  
    //                    this.scrollResponderKeyboardWillShow);  
      
  };

scrollResponderKeyboardWillShow(e) {  
    alert("123");  
  };

  render() {
        var obj = { a: 1 };
var copy = Object.assign({}, obj);
console.log(copy); // { a: 1 }
console.log('hello world');
    return (
<Navigator
        style={{flex:1}}
        initialRoute={{ title: 'My Initial Scene', index: 0 }}
        renderScene={(route, navigator) =>
          <MyScene
            title={route.title}

            // Function to call when a new scene should be displayed
            onForward={() => {    
              const nextIndex = route.index + 1;
              navigator.push({
                title: 'Scene ' + nextIndex,
                index: nextIndex,
              });
            }}

            // Function to call to go back to the previous scene
            onBack={() => {
              if (route.index > 0) {
                navigator.pop();
              }
            }}
          />
        }
      />

    )
  }
}


// 导航栏的Mapper
var NavigationBarRouteMapper = {
  // 左键
  LeftButton(route, navigator, index, navState) {
   
      return (
        <View style={styles.navContainer}>
          <TouchableOpacity
            underlayColor='transparent'>
            <Text style={styles.leftNavButtonText}>
              后退
            </Text>
          </TouchableOpacity>
        </View>
      );
    
  },
  // 右键
  RightButton(route, navigator, index, navState) {
    
      return (
        <View style={styles.navContainer}>
          <TouchableOpacity>
            <Text style={styles.rightNavButtonText}>
              {route.rightText || '右键'}
            </Text>
          </TouchableOpacity>
        </View>
      );
  },
  // 标题
  Title(route, navigator, index, navState) {
    return (
      <View style={styles.navContainer}>
        <Text style={styles.title}>
          应用标题
        </Text>
      </View>
    );
  }
};

var styles = StyleSheet.create({
// 页面框架
  container: {
    flex: 4,
    marginTop: 100,
    flexDirection: 'column'
  },
  // 导航栏
  navContainer: {

    backgroundColor: '#81c04d',
    paddingTop: 12,
    paddingBottom: 10,
  },
  // 导航栏文字
  headText: {
    color: '#ffffff',
    fontSize: 22
  },
  // 按钮
  button: {
    height: 60,
    marginTop: 10,
    justifyContent: 'center', // 内容居中显示
    backgroundColor: '#ff1049',
    alignItems: 'center'
  },
  // 按钮文字
  buttonText: {
    fontSize: 18,
    color: '#ffffff'
  },
  // 左面导航按钮
  leftNavButtonText: {
    color: '#ffffff',
    fontSize: 18,
    marginLeft: 13
  },
  // 右面导航按钮
  rightNavButtonText: {
    color: '#ffffff',
    fontSize: 18,
    marginRight: 13
  },
  // 标题
  title: {
    fontSize: 18,
    color: '#fff',
    textAlign: 'center',
    alignItems: 'center',
    justifyContent: 'center',
    fontWeight: 'bold',
    flex: 1                //Step 3
  }
});


//AppRegistry.registerComponent('SimpleNavigationApp', () => SimpleNavigationApp);

AppRegistry.registerComponent('HelloWorld', () => HelloWorld);