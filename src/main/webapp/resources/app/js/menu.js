(function(){
                var body = $('body'),
                    menuButton = body.find('#toggle-button'),
                    content = body.find('.content'),
                    menuList = body.find('.not-active');
                menuButton.on('click', function(e){
                    menuList.toggleClass('active-ul');
                    content.toggleClass('transf-content');
                });
            })();