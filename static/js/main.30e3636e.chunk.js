(this["webpackJsonpsecure-social"]=this["webpackJsonpsecure-social"]||[]).push([[0],{107:function(e,t,a){e.exports=a(160)},112:function(e,t,a){},113:function(e,t,a){},114:function(e,t,a){},160:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),s=a(45),o=a.n(s),l=a(56),c=a(32),i=(a(112),a(113),a(114),a(33)),u=a.n(i),m=a(47),d=a(13),p=a(14),f=a(16),h=a(15),y=a(31),E=a(17),b=a(93),g=a.n(b),v=a(10),N=a(22),k=a.n(N),w=a(161),P=a(162),j=a(163),K=a(166),O=a(94),C=a(95),x=a.n(C),M=a(29);function U(){v.apps.length||v.initializeApp({apiKey:"AIzaSyBEbt8YqA2fnYO3hft3Z6yMZVyZ6w2IWeM",authDomain:"crest-f8474.firebaseapp.com",databaseURL:"https://crest-f8474.firebaseio.com",projectId:"crest-f8474",storageBucket:"crest-f8474.appspot.com",messagingSenderId:"229758778420",appId:"1:229758778420:web:3c4cfcf53a4634a13d1e61",measurementId:"G-DC2XXBEFJK"})}var S=function(e){function t(e){var n;return Object(d.a)(this,t),(n=Object(f.a)(this,Object(h.a)(t).call(this,e))).loadProfile=function(){var e=Object(m.a)(u.a.mark((function e(t){var a,r,s,o,l;return u.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:for(console.log(t),a=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],r=0;r<a.length;r++)a[r]=t.user.uid.charCodeAt(r);s=new M.Crypt,o=Object(y.a)(n),l=v.firestore(),l.collection("users").doc(t.user.email).get().then((function(e){if(e.exists){var n=new k.a.ModeOfOperation.ctr(a).decrypt(e.data().privateKey.toUint8Array()),r=k.a.utils.utf8.fromBytes(n),c=s.decrypt(r,e.data().postKey).message.split(",").map((function(e){return parseInt(e)}));o.setState({loggedIn:!0,userUid:t.user.uid,userName:t.user.displayName,userPhoto:t.user.photoURL,userEmail:t.user.email,userToken:t.credential.idToken,userPublicKey:e.data().publicKey,userPrivateKey:r,postKey:c,friends:e.data().friends,requested:e.data().requested})}else{var i,u;(new M.RSA).generateKeyPairAsync().then((function(e){i=e.publicKey,u=e.privateKey})).then((function(){var e=new k.a.ModeOfOperation.ctr(a),n=k.a.utils.utf8.toBytes(u),r=e.encrypt(n),c=Array.from({length:32},(function(){return Math.floor(256*Math.random())})),m=s.encrypt(i,c),d={displayName:t.user.displayName,photoURL:t.user.photoURL,email:t.user.email,publicKey:i,privateKey:v.firestore.Blob.fromUint8Array(r),postKey:m,friends:[],requested:[]};l.collection("users").doc(t.user.email).set(d).then((function(){return o.setState({loggedIn:!0,userUid:t.user.uid,userName:t.user.displayName,userPhoto:t.user.photoURL,userEmail:t.user.email,userToken:t.credential.idToken,userPublicKey:i,userPrivateKey:u,postKey:c,friends:[],requested:[]})}))}))}})).catch((function(e){console.log("Error getting document",e)}));case 8:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),n.responseGoogle=function(e){console.log(e);var t=v.auth.GoogleAuthProvider.credential(e.tc.id_token),a=Object(y.a)(n);v.auth().signInWithCredential(t).then((function(e){return a.loadProfile(e)})).catch((function(e){var t=e.code,a=e.message,n=e.email,r=e.credential;console.log("Error "+t+": "+a),console.log("Email: "+n),console.log("Credential: "+r)}))},n.responseFacebook=function(e){console.log(e);var t=v.auth.FacebookAuthProvider.credential(e.accessToken),a=Object(y.a)(n);v.auth().signInWithCredential(t).then((function(e){return a.loadProfile(e)})).catch((function(e){var t=e.code,a=e.message,n=e.email,r=e.credential;console.log("Error "+t+": "+a),console.log("Email: "+n),console.log("Credential: "+r)}))},a(145).config({debug:!0}),n.state={loggedIn:!1,friends:[],userUid:null,userName:null,userPhoto:null,userEmail:null,userToken:null},U(),n}return Object(E.a)(t,e),Object(p.a)(t,[{key:"render",value:function(){var e=this.state,t=e.loggedIn,a=e.userUid,n=e.userName,s=e.userPhoto,o=e.userEmail,l=e.userToken,i=e.userPublicKey,u=e.userPrivateKey,m=e.friends,d=e.requested,p=e.postKey;return r.a.createElement("div",{className:"App"},t&&r.a.createElement(c.a,{to:{pathname:"/profile",state:{userUid:a,userName:n,userPhoto:s,userEmail:o,userToken:l,userPublicKey:i,userPrivateKey:u,postKey:p,friends:m,requested:d}}}),r.a.createElement(w.a,null,r.a.createElement(P.a,{className:"justify-content-center"},r.a.createElement(j.a,{className:"col-md-9 col-lg-12 col-xl-10 justify-content-center"},r.a.createElement(K.a,{className:"shadow-lg o-hidden border-0 my-5 justify-content-center"},r.a.createElement(K.a.Body,{className:"p-0 justify-content-center"},r.a.createElement(P.a,{className:"justify-content-center"},r.a.createElement(j.a,{className:"col-lg-6 justify-content-center"},r.a.createElement("div",{className:"p-5 justify-content-center"},r.a.createElement("div",{className:"text-center justify-content-center"},r.a.createElement("h4",{className:"text-dark mb-4"},"Secure Social")),r.a.createElement(O.GoogleLogin,{clientId:"229758778420-kfodakij699oncpbvaoppge2hj56oe6f.apps.googleusercontent.com",render:function(e){return r.a.createElement("button",{onClick:e.onClick,disabled:e.disabled,className:"btn btn-primary btn-block text-white btn-google btn-user"},r.a.createElement("i",{className:"fab fa-google"}),"\xa0 Login with Google")},onSuccess:this.responseGoogle,onFailure:this.responseGoogle,cookiePolicy:"single_host_origin"}),r.a.createElement(x.a,{appId:"649543218885961",autoLoad:!1,cssClass:"btn btn-primary btn-block text-white btn-facebook btn-user",icon:"fa-facebook",style:{textSpacing:"10px"},callback:this.responseFacebook}))),r.a.createElement(j.a,{className:"col-lg-6 justify-content-center"},r.a.createElement("img",{src:g.a,alt:"logo",style:{backgroundColor:"transparent",paddingTop:"5vh",height:"30vh"}})))))))))}}]),t}(r.a.Component),F=a(164),I=a(106),A=r.a.forwardRef((function(e,t){var a=e.children,n=e.onClick;return r.a.createElement("a",{href:"/",ref:t,onClick:function(e){e.preventDefault(),n(e)}},a)})),q=function(e){function t(e){var a;return Object(d.a)(this,t),(a=Object(f.a)(this,Object(h.a)(t).call(this,e))).acceptFriend=function(e,t,n,r,s){var o=v.firestore(),l=o.collection("users").doc(a.state.userEmail),c=new M.Crypt;n=c.decrypt(a.state.userPrivateKey,n).message.split(",").map((function(e){return parseInt(e)})),l.get().then((function(e){return e.data()})).then((function(t){var a=t.requested.filter((function(t){return t.email!==e}));return t.requested=a,t})).then((function(s){return s.friends.push({email:e,name:t,profilePic:r,postKey:c.encrypt(a.state.userPublicKey,n)}),s})).then((function(e){return l.set(e)}));var i=o.collection("users").doc(e);i.get().then((function(e){return e.data()})).then((function(e){return e.friends.push({email:a.state.userEmail,name:a.state.userName,profilePic:a.state.userPhoto,postKey:c.encrypt(s,a.state.postKey)}),e})).then((function(e){return i.set(e)}))},a.declineFriend=function(e){var t=v.firestore().collection("users").where("email","==",e);t.get().then((function(e){return e.data()})).then((function(t){var a=t.requested.filter((function(t){return t.email!==e}));return t.requested=a,t})).then((function(e){return t.set(e)}))},a.state=a.props.state,a}return Object(E.a)(t,e),Object(p.a)(t,[{key:"componentDidMount",value:function(){this.listenForFriendRequests()}},{key:"componentDidUpdate",value:function(){this.listenForFriendRequests()}},{key:"listenForFriendRequests",value:function(){var e=this;v.firestore().collection("users").doc(this.state.userEmail).onSnapshot((function(t){var a=t.data();e.setState({requested:a.requested,friends:a.friends})}),(function(e){console.log("Encountered error: ".concat(e))}))}},{key:"render",value:function(){var e=this,t=this.state,a=t.requested,n=t.loadContacts,s=t.loadProfile,o=t.userEmail,l=t.userName,i=t.userPhoto,u=t.userToken,m=t.userPublicKey,d=t.userPrivateKey,p=t.postKey,f=t.userUid,h=t.friends;return r.a.createElement("div",null,s&&r.a.createElement(c.a,{to:{pathname:"/profile",state:{userUid:f,userName:l,userPhoto:i,userEmail:o,userToken:u,userPublicKey:m,userPrivateKey:d,postKey:p,friends:h}}}),n&&r.a.createElement(c.a,{to:{pathname:"/contacts",state:{userUid:f,userName:l,userPhoto:i,userEmail:o,userToken:u,userPublicKey:m,userPrivateKey:d,postKey:p,friends:h,requested:a}}}),r.a.createElement("nav",{className:"navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0"},r.a.createElement(w.a,{fluid:!0,className:"container-fluid d-flex flex-column p-0"},r.a.createElement("div",{className:"navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0"},r.a.createElement("div",{className:"sidebar-brand-icon rotate-n-15"},r.a.createElement("i",{className:"fas fa-laugh-wink"})),r.a.createElement("div",{className:"sidebar-brand-text mx-3"},r.a.createElement("span",null,"Secure Social"))),r.a.createElement("hr",{className:"sidebar-divider my-0"}),r.a.createElement("ul",{className:"nav navbar-nav text-light",id:"accordionSidebar"},r.a.createElement("li",{className:"nav-item",role:"presentation"},r.a.createElement("div",{className:"nav-link",onClick:function(){return e.setState({loadProfile:!0,loadContacts:!1})}},r.a.createElement("i",{className:"fas fa-user"}),r.a.createElement("span",null,"Profile"))),r.a.createElement("li",{className:"nav-item",role:"presentation"},r.a.createElement("div",{className:"nav-link",onClick:function(){return e.setState({loadContacts:!0,loadProfile:!1})}},r.a.createElement("i",{className:"fas fa-user-lock"}),r.a.createElement("span",null,"Contacts"))),r.a.createElement("li",null,r.a.createElement(F.a,{style:{paddingLeft:"8%"}},r.a.createElement(F.a.Toggle,{as:A,id:"dropdown-basic"},r.a.createElement("i",{style:{color:"white",paddingTop:"10%"},className:"fas fa-user-friends"}),r.a.createElement("span",{className:"badge badge-danger badge-counter"},a?a.length:0)),r.a.createElement(F.a.Menu,null,a&&a.map((function(t){var a=t.email,n=t.name,s=t.postKey,o=t.profilePic,l=t.publicKey;return r.a.createElement("div",{key:a},r.a.createElement(w.a,{fluid:!0,style:{}},r.a.createElement(P.a,{xs:4,md:6,lg:8},r.a.createElement(j.a,{sm:!0},r.a.createElement("img",{style:{width:"20px",height:"auto"},src:o,alt:"Profile"})),r.a.createElement(j.a,{sm:!0},n),r.a.createElement(j.a,{sm:!0,className:"m-0 p-0"},r.a.createElement(I.a,{style:{width:"20px",height:"20px",padding:"0"},className:"fas fa-plus",onClick:function(){return e.acceptFriend(a,n,s,o,l)}})),r.a.createElement(j.a,{sm:!0,className:"m-0 p-0"},r.a.createElement(I.a,{style:{width:"20px",height:"20px",padding:"0"},className:"fas fa-times",onClick:function(){return e.declineFriend(a)}})))),r.a.createElement("hr",null))})))))),r.a.createElement("div",{className:"text-center d-none d-md-inline"}))))}}]),t}(r.a.Component),B=a(165),T=function(e){function t(e){var a;return Object(d.a)(this,t),(a=Object(f.a)(this,Object(h.a)(t).call(this,e))).postMessage=function(e){e.preventDefault();var t=new k.a.ModeOfOperation.ctr(a.props.postKey),n=k.a.utils.utf8.toBytes(a.state.value),r=t.encrypt(n),s={timestamp:Date.now(),content:v.firestore.Blob.fromUint8Array(r),poster:{name:a.props.userName,email:a.props.email,profilePic:a.props.userPhoto}};a.state.db.collection("posts").doc().set(s)},a.handleChange=function(e){a.setState({value:e.target.value})},a.state={value:"",posts:[],collection:v.firestore().collection("posts"),db:v.firestore()},a}return Object(E.a)(t,e),Object(p.a)(t,[{key:"componentDidMount",value:function(){this.listenForPosts()}},{key:"componentDidUpdate",value:function(){var e=Object(m.a)(u.a.mark((function e(){return u.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,this.listenForPosts();case 2:case"end":return e.stop()}}),e,this)})));return function(){return e.apply(this,arguments)}}()},{key:"listenForPosts",value:function(){var e=Object(m.a)(u.a.mark((function e(){var t,a,n,r;return u.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return t=this,a=new M.Crypt,e.next=4,v.firestore().collection("posts").get();case 4:n=e.sent,r=n.docs.map((function(e){var n=e.data(),r=null;if(t.props.friends.forEach((function(e){if(e.email===n.poster.email){r=a.decrypt(t.props.userPrivateKey,e.postKey).message.split(",").map((function(e){return parseInt(e)}));var s=new k.a.ModeOfOperation.ctr(r).decrypt(n.content.toUint8Array()),o=k.a.utils.utf8.fromBytes(s);n.content=o}})),n.poster.email===t.props.email){var s=new k.a.ModeOfOperation.ctr(t.props.postKey).decrypt(n.content.toUint8Array()),o=k.a.utils.utf8.fromBytes(s);n.content=o}else{var l=n.content.toUint8Array(),c=new TextDecoder("utf-8").decode(l);n.content="Encrypted: "+c}return n})),this.setState({posts:r});case 7:case"end":return e.stop()}}),e,this)})));return function(){return e.apply(this,arguments)}}()},{key:"render",value:function(){var e=this.state,t=e.value,a=e.posts;return r.a.createElement("div",null,r.a.createElement("form",{className:"form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search",style:{width:"95%"},onSubmit:this.postMessage},r.a.createElement("div",{className:"input-group"},r.a.createElement("input",{id:"username",type:"text",className:"bg-light form-control border-0 small",placeholder:"New Post...",value:t,onChange:this.handleChange}),r.a.createElement("div",{className:"input-group-append"},r.a.createElement(I.a,{className:"btn btn-primary py-0",type:"submit",value:"Send"},r.a.createElement("i",{className:"fas fa-paper-plane"}))))),r.a.createElement(B.a,null,a&&a.map((function(e,t){return r.a.createElement(B.a.Item,{key:t},r.a.createElement(P.a,null,r.a.createElement(j.a,{sm:!0,style:{margin:"0px"}},r.a.createElement("img",{src:e.poster.profilePic,style:{width:"24px",height:"auto"},alt:"Profile"})),r.a.createElement(j.a,{xs:10},e.content)))}))))}}]),t}(r.a.Component),R=function(e){function t(e){var a;return Object(d.a)(this,t),(a=Object(f.a)(this,Object(h.a)(t).call(this,e))).state=a.props.location.state,U(),a}return Object(E.a)(t,e),Object(p.a)(t,[{key:"render",value:function(){var e=this.state,t=e.userEmail,a=e.userName,n=e.userPhoto,s=e.friends,o=e.postKey,l=e.userPrivateKey;return r.a.createElement("div",{id:"wrapper"},r.a.createElement(q,{state:this.state}),r.a.createElement(j.a,{className:"d-flex flex-column"},r.a.createElement(w.a,{fluid:!0},r.a.createElement(P.a,{className:"row mb-3"},r.a.createElement(j.a,{className:"col-lg-4"},r.a.createElement(K.a,{className:"card mb-3"},r.a.createElement(K.a.Body,{className:"text-center shadow"},r.a.createElement("img",{className:"rounded-circle mb-3 mt-4",src:n,alt:"Profile",width:"160",height:"160"}))),r.a.createElement(K.a,{className:"card shadow mb-4"},r.a.createElement(K.a.Header,{className:"card-header py3"},r.a.createElement("h6",{className:"text-primary font-weight-bold m-0"},"Profile Information")),r.a.createElement(K.a.Body,null,r.a.createElement(P.a,null,"Name: ",a),r.a.createElement(P.a,null,"Email Address: ",t),r.a.createElement(P.a,null,"Friends: ",s.length)))),r.a.createElement(j.a,{className:"col-lg-8"},r.a.createElement(P.a,{className:"row"},r.a.createElement(j.a,{className:"col"},r.a.createElement(K.a,{className:"card shadow mb-3"},r.a.createElement(K.a.Header,null,"Post Board"),r.a.createElement(T,{userName:a,userPhoto:n,friends:s,postKey:o,userPrivateKey:l,email:t})))))))))}}]),t}(r.a.Component),D=function(e){function t(){return Object(d.a)(this,t),Object(f.a)(this,Object(h.a)(t).apply(this,arguments))}return Object(E.a)(t,e),Object(p.a)(t,[{key:"render",value:function(){return r.a.createElement(B.a,null,this.props.friends&&this.props.friends.map((function(e){var t=e.userEmail,a=e.name,n=e.profilePic;return r.a.createElement(B.a.Item,{key:t},r.a.createElement(P.a,null,r.a.createElement(j.a,null,r.a.createElement("img",{src:n,alt:"Profile",style:{width:"64px",height:"auto"}})),r.a.createElement(j.a,null,a),r.a.createElement(j.a,null,t)))})))}}]),t}(r.a.Component),L=a(71),G=a.n(L),W={content:{top:"50%",left:"50%",width:"40%",right:"auto",bottom:"auto",marginRight:"-50%",transform:"translate(-50%, -50%)"}},H=function(e){function t(){return Object(d.a)(this,t),Object(f.a)(this,Object(h.a)(t).apply(this,arguments))}return Object(E.a)(t,e),Object(p.a)(t,[{key:"render",value:function(){var e=this;return G.a.setAppElement("#root"),r.a.createElement(G.a,{isOpen:this.props.showModal,contentLabel:"Add Contact",style:W},r.a.createElement("h4",null,"Add Contact"),r.a.createElement("hr",null),r.a.createElement(B.a,null,this.props.people&&this.props.people.map((function(t){var a=t.displayName,n=t.photoURL,s=t.email,o=t.publicKey;return r.a.createElement(B.a.Item,{key:s,onClick:function(){return e.props.addFriend(s,o)}},r.a.createElement(P.a,null,r.a.createElement(j.a,null,r.a.createElement("img",{src:n,alt:"Profile",style:{width:"32px",height:"auto"}})),r.a.createElement(j.a,null,a),r.a.createElement(j.a,null,s)))}))),r.a.createElement("hr",null),r.a.createElement(I.a,{onClick:this.props.handleCloseModal},"close"))}}]),t}(r.a.Component),J=function(e){function t(e){var a;return Object(d.a)(this,t),(a=Object(f.a)(this,Object(h.a)(t).call(this,e))).addFriend=function(e,t){var n=new M.Crypt,r=v.firestore();r.collection("users").doc(e).get().then((function(e){return console.log(e),e})).then((function(e){return e.data()})).then((function(s){console.log(s);var o=s.requested;o.push({email:a.state.userEmail,name:a.state.userName,profilePic:a.state.userPhoto,publicKey:a.state.userPublicKey,postKey:n.encrypt(t,a.state.postKey)}),s.requested=o,r.collection("users").doc(e).set(s)}))},a.state=a.props.location.state,a.handleCloseModal=a.handleCloseModal.bind(Object(y.a)(a)),U(),a}return Object(E.a)(t,e),Object(p.a)(t,[{key:"componentDidMount",value:function(){this.setState({showModal:!1})}},{key:"componentDidUpdate",value:function(){var e=this,t=[],a=this,n=v.firestore();n.collection("users").get().then((function(r){r.docs.forEach((function(s,o){return n.collection("users").doc(s.id).get().then((function(n){n.id!==e.state.userEmail&&t.push({displayName:n.data().displayName,photoUrl:n.data().photoURL,email:n.id,publicKey:n.data().publicKey}),o===r.docs.length-1&&a.setState({people:t})}))}))}))}},{key:"handleCloseModal",value:function(){this.setState({showModal:!1})}},{key:"render",value:function(){var e=this,t=this.state,a=t.friends,n=t.people,s=t.showModal;return r.a.createElement("div",{id:"wrapper"},r.a.createElement(q,{state:this.state}),r.a.createElement(j.a,{className:"d-flex flex-column"},r.a.createElement(w.a,{fluid:!0},r.a.createElement("h3",{className:"text-dark mb-4"},"Contacts"),r.a.createElement(P.a,{className:"row mb-3"},r.a.createElement(j.a,{className:"col"},r.a.createElement(P.a,{className:"row"},r.a.createElement(j.a,{className:"col"},r.a.createElement(K.a,{className:"card shadow"},r.a.createElement(K.a.Header,null,r.a.createElement(P.a,null,r.a.createElement(j.a,{style:{paddingTop:0,paddingBottom:0,marginBottom:0}},r.a.createElement("h5",{className:"text-dark mt-2"},"Contacts")),r.a.createElement(j.a,{className:"justify-content-right pull-right"},r.a.createElement(I.a,{className:"btn-primary pull-right float-right",onClick:function(){return e.setState({showModal:!0})}},"Add Contact"),r.a.createElement(H,{people:n,showModal:s,handleCloseModal:this.handleCloseModal,addFriend:this.addFriend})))),r.a.createElement(K.a.Body,null,r.a.createElement(w.a,null,r.a.createElement(D,{friends:a})))))))))))}}]),t}(r.a.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));var Z=r.a.createElement(l.a,null,r.a.createElement("div",null,r.a.createElement(c.b,{path:"/login",component:S}),r.a.createElement(c.b,{path:"/profile",component:R}),r.a.createElement(c.b,{path:"/contacts",component:J})));o.a.render(Z,document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))},64:function(e,t){},93:function(e,t,a){e.exports=a.p+"static/media/logo.bef441a2.png"}},[[107,1,2]]]);
//# sourceMappingURL=main.30e3636e.chunk.js.map