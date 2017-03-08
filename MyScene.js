import React, { Component, PropTypes } from 'react';
import { View, Text, TouchableHighlight,NativeModules,StyleSheet } from 'react-native';
import ListViewExample from './ListViewExample';
 var rnToastAndroid = NativeModules.RNCommunicationModule;



export default class MyScene extends Component {

  clickButton(){
    rnToastAndroid.show('Hello Toast of native', rnToastAndroid.SHORT);
  }

  render() {
    return (
      

      <View >



      <View style={styles.navContainer}> 

               <View style={styles.leftBTN}> 
          <Text >Left</Text>
        </View>
        <View style={styles.navTitle}> 
          <Text style={{textAlign:'center'}}>Title</Text>
        </View>

         <View style={styles.rightBTN}> 
          <Text style={{textAlign:'right'}}>Right</Text>
        </View>

       </View>

        <Text>Current Scene: {this.props.title}</Text>

        <TouchableHighlight onPress={this.props.onForward}>
          <Text>Tap me to load the next scene</Text>
        </TouchableHighlight>

        <TouchableHighlight onPress={this.props.onBack}>
          <Text>Tap me to go back</Text>
        </TouchableHighlight>
       <Text onPress={this.clickButton} style={styles.hello} >Hello, World</Text>

        <View><Text>dfdf</Text>
        <ListViewExample /></View>
      </View>
      
    )
  }
}

var styles = StyleSheet.create({
  navContainer:{

    flexDirection:'row',
    margin:10,
  },
  rightBTN:{
    flex:1,
    
  },
  leftBTN:{
    flex:1,
  },
  navTitle:{
    flex:1,
    
  },
  container222: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

MyScene.propTypes = {
  title: PropTypes.string.isRequired,
  onForward: PropTypes.func.isRequired,
  onBack: PropTypes.func.isRequired,
};