//Definindo um modulo para a aplicacao
var app = angular.module('musikais', []);

app.controller('musikaisController', function($scope, $http) {
    $scope.path_img = 'imagens/';
    $scope.path_mus = 'music/';
    getImage(); //Carrega todas as imagens disponíveis
    getMusic(); //Carrega todas as musicas disponíveis
    getRank(); //Carrega o rank das votações
    function getImage(){  
        $http.post("ajax/getImages.php").success(function(data){
            $scope.images = data;
            $scope.img = $scope.images[Math.floor(Math.random()*$scope.images.length)];
        });
    };
    function getMusic(){  
        $http.post("ajax/getMusics.php").success(function(data){
            $scope.musics = data;
            $scope.music = $scope.musics[Math.floor(Math.random()*$scope.musics.length)];            
            $scope.music2 = $scope.musics[Math.floor(Math.random()*$scope.musics.length)];            
        });
    };
    function getRank(){  
        $http.post("ajax/getRank.php").success(function(data){
            $scope.rank = data;            
        });
    };
    $scope.addVote = function (music,img,vote) {
        $http.post("ajax/addVote.php?music="+music+"&image="+img+"&vote="+vote).success(function(data){
            //getImage();
            //getMusic();
            location.reload();
            
        });
    };
});